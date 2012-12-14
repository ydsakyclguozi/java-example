/*
 * Copyright (c) 2012 Kengo Nakatsuka <kengo.nakatsuka@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package jp.nak2k.example.aws.s3;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import jp.nak2k.example.aws.auth.XMLPropertiesCredentials;

import org.junit.Test;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

/**
 * @author Kengo Nakatsuka <kengo.nakatsuka@gmail.com>
 * 
 */
public class S3ClientTest {

	@Test
	public void test() throws IOException {
		AWSCredentials credentials = new XMLPropertiesCredentials(S3ClientTest.class);

		AmazonS3Client s3Client = new AmazonS3Client(credentials);
		List<Bucket> buckets = s3Client.listBuckets();

		System.out.println("buckets.size() : " + buckets.size());
		assertFalse(buckets.isEmpty());

		for (Bucket bucket : buckets) {
			System.out.println("bucket: " + bucket.getName());
		}
	}
}
