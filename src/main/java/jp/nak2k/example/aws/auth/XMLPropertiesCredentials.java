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

package jp.nak2k.example.aws.auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;

/**
 * XML で書かれた properties file を読み込んで {@link AWSCredentials} を構築します。
 * {@link PropertiesCredentials} の代わりとして使えます。
 * 
 * @author Kengo Nakatsuka <kengo.nakatsuka@gmail.com>
 * 
 */
public class XMLPropertiesCredentials implements AWSCredentials {
	/**
	 * 
	 */
	private static final String DEFAULT_RESOURCE_NAME = "aws.xml";

	private String accessKey;
	private String secretKey;

	/**
	 * XML properties file を指定された {@link InputStream} から読み込んで
	 * {@link XMLPropertiesCredentials} オブジェクトを構築します。
	 * 
	 * @param in
	 * @throws IOException
	 * @throws InvalidPropertiesFormatException
	 */
	public XMLPropertiesCredentials(InputStream in) throws InvalidPropertiesFormatException, IOException {
		if (in == null) {
			throw new IllegalArgumentException("'in' should not be null.");
		}
		loadXMLProperties(in);
	}

	/**
	 * XML properties file を指定されたクラスを基準とした指定されたファイル名のリソースストリームから読み込んで
	 * {@link XMLPropertiesCredentials} オブジェクトを構築します。
	 * 
	 * @param klass
	 * @param filename
	 * @throws IOException
	 * @throws InvalidPropertiesFormatException
	 */
	public XMLPropertiesCredentials(Class<?> klass, String filename) throws InvalidPropertiesFormatException,
			IOException {
		InputStream in = klass.getResourceAsStream(filename);
		if (in == null) {
			throw new IllegalArgumentException(filename + " not found.");
		}
		loadXMLProperties(in);
	}

	/**
	 * XML properties file を指定されたクラスを基準としたリソース aws.xml から読み込んで
	 * {@link XMLPropertiesCredentials} オブジェクトを構築します。
	 * 
	 * @param klass
	 * @throws InvalidPropertiesFormatException
	 * @throws IOException
	 */
	public XMLPropertiesCredentials(Class<?> klass) throws InvalidPropertiesFormatException, IOException {
		InputStream in = klass.getResourceAsStream(DEFAULT_RESOURCE_NAME);
		if (in == null) {
			throw new IllegalArgumentException("aws.xml not found.");
		}
		loadXMLProperties(in);
	}

	/**
	 * @param in
	 * @throws IOException
	 * @throws InvalidPropertiesFormatException
	 */
	private void loadXMLProperties(InputStream in) throws IOException, InvalidPropertiesFormatException {
		Properties props = new Properties();
		try {
			props.loadFromXML(in);
		} finally {
			IOUtils.closeQuietly(in);
		}

		String accessKey = props.getProperty("accessKey");
		String secretKey = props.getProperty("secretKey");

		if (accessKey == null || secretKey == null) {
			throw new IllegalArgumentException("accessKey and secretKey should exist.");
		}

		this.accessKey = accessKey;
		this.secretKey = secretKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amazonaws.auth.AWSCredentials#getAWSAccessKeyId()
	 */
	@Override
	public String getAWSAccessKeyId() {
		return accessKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amazonaws.auth.AWSCredentials#getAWSSecretKey()
	 */
	@Override
	public String getAWSSecretKey() {
		return secretKey;
	}

}
