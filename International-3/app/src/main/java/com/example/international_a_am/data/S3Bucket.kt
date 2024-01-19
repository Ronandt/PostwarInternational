package com.example.international_a_am.data

import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.smithy.kotlin.runtime.auth.awscredentials.Credentials
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import aws.smithy.kotlin.runtime.collections.Attributes
import aws.smithy.kotlin.runtime.content.ByteStream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class Provider(): CredentialsProvider {
    override suspend fun resolve(attributes: Attributes): Credentials {
       return Credentials.invoke("AKIAS7JH7M4XUBEDOJ5V", secretAccessKey = "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")
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


}