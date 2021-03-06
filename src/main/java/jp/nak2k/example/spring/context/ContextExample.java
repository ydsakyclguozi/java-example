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

package jp.nak2k.example.spring.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Kengo Nakatsuka <kengo.nakatsuka@gmail.com>
 * 
 */
public class ContextExample {
	/**
	 * Bean 定義のXMLファイルを読み込みます。
	 * 
	 * @return
	 */
	public ApplicationContext loadContext() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"jp/nak2k/example/spring/context/application.xml");
		return context;
	}

	/**
	 * Bean 定義のXMLファイルを読み込みます。
	 * <p>
	 * 定義ファイルの位置をこのクラスを基準に指定します。
	 * 
	 * @return
	 */
	public ApplicationContext loadContext2() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"application.xml", ContextExample.class);
		return context;
	}

	/**
	 * Java のクラスで定義した bean 定義を読み込みます。
	 * <p>
	 * ここでは {@link Configuration} アノテーションをつけている {@link Config} クラスが定義として読み込まれます。
	 * {@link Configuration} アノテーションを使う場合、 cglib.jar が必要です。
	 * 
	 * @return
	 */
	public ApplicationContext loadContextFromJava() {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				ContextExample.class.getPackage().getName());
		return context;
	}
}
