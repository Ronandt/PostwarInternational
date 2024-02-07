package com.example.international_a_am

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.international_a_am.ui.theme.LightGray
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun LazyListState.getListVisibleItemInfoFor(absolute: Int): LazyListItemInfo? {
return this.layoutInfo.visibleItemsInfo.getOrNull(absolute - this.layoutInfo.visibleItemsInfo.first().index )


}

val LazyListItemInfo.offsetEnd: Int
    get() = this.offset + this.size

fun <T> MutableList<T>.move(from: Int, to :Int) {
    if( from == to)
        return

    val element = this.removeAt(from) ?: return
    this.add(to, element)
}


@Composable
fun rememberDragDropListState(
    lazyListState: LazyListState = rememberLazyListState(),
    onMove: (Int, Int) -> Unit
): DragDropListState {
    return remember { DragDropListState(lazyListState =  lazyListState, onMove = onMove) }
}
class DragDropListState(

    val lazyListState: LazyListState,
    private val onMove: (Int, Int) -> Unit
) {
    var draggedDistance by mutableStateOf(0f)
    var initiallyDraggedElement by mutableStateOf<LazyListItemInfo?>(null)
    var currentIndexOfDraggedItem by mutableStateOf<Int?>(null)
    val initialOffsets: Pair<Int, Int>?
        get() = initiallyDraggedElement?.let {
            Pair(it.offset, it.offsetEnd)

        }
    val elementDisplacement: Float?
        get() = currentIndexOfDraggedItem?.let {
            lazyListState.getListVisibleItemInfoFor(absolute = it)
        }?.let {
            item -> (initiallyDraggedElement?.offset ?: 0f).toFloat() + draggedDistance - item.offset
        }

    val currentElement: LazyListItemInfo?
        get() =currentIndexOfDraggedItem?.let {
            lazyListState.getListVisibleItemInfoFor(absolute = it)
        }

    var overScrollJob by mutableStateOf<Job?>(null)

    fun onDragStart(offset: Offset) {
        lazyListState.layoutInfo.visibleItemsInfo.firstOrNull() {
            item ->
            offset.y.toInt() in item.offset..(item.offset + item.size)
        }?. also {
            currentIndexOfDraggedItem = it.index
            initiallyDraggedElement = it
        }
    }

    fun onDragInterrupted() {
        draggedDistance = 0f
        currentIndexOfDraggedItem = null
        initiallyDraggedElement = null
        overScrollJob?.cancel()
    }

    fun onDrag(offset:Offset) {
        draggedDistance += offset.y
        initialOffsets?.let {
            (topOffset, bottomOffset) ->
            val startOffset = topOffset + draggedDistance
            val endOffset = bottomOffset + draggedDistance
            currentElement?.let {
                hovered -> lazyListState.layoutInfo.visibleItemsInfo
                .filterNot {
                    item -> item.offsetEnd < startOffset || item.offset > endOffset || hovered.index == item.index
                }
                .firstOrNull {
                    item ->
                    val delta = startOffset - hovered.offset
                    when {
                        delta> 0 -> (endOffset > item.offsetEnd)
                        else -> (startOffset < item.offset)
                    }
                }?.also {
                    item -> currentIndexOfDraggedItem?.let {
                        current -> onMove.invoke(current, item.index)
                }
                    currentIndexOfDraggedItem = item.index

                }
            }
        }
    }

    fun checkForOverScroll(): Float {
        return initiallyDraggedElement?. let {
            val startOffset = it.offset + draggedDistance
            val endOffset = it.offsetEnd + draggedDistance
            return@let when {
                draggedDistance > 0 -> (endOffset - lazyListState.layoutInfo.viewportEndOffset).takeIf {
                    diff -> diff > 0
                }
                draggedDistance < 0 -> (startOffset - lazyListState.layoutInfo.viewportStartOffset).takeIf {
                    diff -> diff < 0
                }
                else -> null
            }
        } ?: 0f
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T>DragDropList(
    items: List<T>,
    onMove: (Int, Int) -> Unit,

    lazyListState: LazyListState,
    modifier: Modifier = Modifier,
     content: @Composable (T) -> Unit

) {
    val scope = rememberCoroutineScope()
    var overScrollJob by remember{
        mutableStateOf<Job?>(null)
    }
    val dragDropListState = rememberDragDropListState(onMove = onMove, lazyListState = lazyListState)

    LazyColumn(modifier = modifier
        .pointerInput(Unit) {
            detectDragGesturesAfterLongPress(onDrag = { change, offset ->
                change.consumeAllChanges()
                dragDropListState.onDrag(offset = offset)
                if (overScrollJob?.isActive == true) return@detectDragGesturesAfterLongPress
                dragDropListState
                    .checkForOverScroll()
                    .takeIf {
                        it != 0f
                    }
                    ?.let {
                        overScrollJob = scope.launch {
                            dragDropListState.lazyListState.scrollBy(it)
                        }
                    } ?: kotlin.run {
                    overScrollJob?.cancel()
                }
            },

                onDragStart = { offset -> dragDropListState.onDragStart(offset) },
                onDragEnd = { dragDropListState.onDragInterrupted() },
                onDragCancel = { dragDropListState.onDragInterrupted() })
        }
        .fillMaxWidth().background(LightGray, RoundedCornerShape(14.dp))
        .padding(top = 10.dp, start = 10.dp, end = 10.dp), state = dragDropListState.lazyListState) {
        itemsIndexed(items) {
            index,item ->
            val dragModifier =
            Modifier
            .composed {
                val offsetOrNull = dragDropListState.elementDisplacement.takeIf {
                    index == dragDropListState.currentIndexOfDraggedItem
                }
                val draggedItemState = dragDropListState.currentElement
                Modifier.graphicsLayer {
                    translationY = offsetOrNull ?: 0f
                }
            }

            Column(
                modifier = dragModifier.animateItemPlacement()
                   )
             {
               content(item)
            }
            

        }
    }
}