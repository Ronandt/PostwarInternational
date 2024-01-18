package com.example.international_a_am.data

import aws.sdk.kotlin.services.s3.S3Client

class S3Bucket {
    val REGION = "ap-southeast-1"
    val BUCKET = "bucket"

    suspend fun upload(fileName: String) {
     S3Client.fromEnvironment { region = REGION}
    }
}