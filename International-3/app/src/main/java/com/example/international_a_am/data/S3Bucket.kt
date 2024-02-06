package com.example.international_a_am.data

import android.content.Context
import android.os.Environment
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.GetObjectRequest
import aws.sdk.kotlin.services.s3.model.ListObjectsRequest
import aws.sdk.kotlin.services.s3.model.Object
import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.smithy.kotlin.runtime.auth.awscredentials.Credentials
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import aws.smithy.kotlin.runtime.collections.Attributes
import aws.smithy.kotlin.runtime.content.ByteStream
import aws.smithy.kotlin.runtime.content.writeToFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.UUID

class Provider(): CredentialsProvider {
    override suspend fun resolve(attributes: Attributes): Credentials {
       return Credentials.invoke("AKIAS7JH7M4X3FDJ2XOQ", secretAccessKey = "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")
    }

}
class S3Bucket {
    val REGION = "ap-southeast-1"
    val BUCKET = "worldskills-international-assets"
    val serveLink = "https://d3izhzxqbknro4.cloudfront.net"


    suspend fun upload(byteStream: ByteStream, fileName: String = "upload-" + UUID.randomUUID().toString()): String {

       return withContext(Dispatchers.IO) {
            val request = PutObjectRequest {
                bucket = BUCKET
                key = fileName
                body = byteStream
            }

            S3Client.fromEnvironment { region = REGION
                credentialsProvider = Provider()
            }.use {
                    s3 -> s3.putObject(request)

            }
            return@withContext "$serveLink/$fileName"
        }
        }
     //   Credentials.invoke()


    suspend fun listItems(): List<Object>? {
        return withContext(Dispatchers.IO) {
            val request = ListObjectsRequest {
                bucket = BUCKET
            }

            S3Client { region = REGION
                credentialsProvider = Provider()
            }.use { s3 ->

                val response = s3.listObjects(request)
                return@use response.contents
            }
        }

    }

    suspend fun getItems(keyN: String, context: Context):File {
        return withContext(Dispatchers.IO) {
            val request = GetObjectRequest {
                key = keyN
                bucket = BUCKET
            }

            S3Client { region = REGION
                credentialsProvider = Provider()
            }.use { s3 ->
                s3.getObject(request) { resp ->
                    val myFile = File(context.getExternalFilesDir(Environment.DIRECTORY_MUSIC),"/tmp")
                    resp.body?.writeToFile(myFile)
                    return@getObject myFile
                }
            }
        }
    }


}