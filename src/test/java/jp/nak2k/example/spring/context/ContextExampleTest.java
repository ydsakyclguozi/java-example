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

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * @author Kengo Nakatsuka <kengo.nakatsuka@gmail.com>
 * 
 */
public class ContextExampleTest {

	@Test
	public void testLoadContext() {
		ContextExample example = new ContextExample();

		ApplicationContext context = example.loadContext();
		Foo foo = (Foo) context.getBean("foo");
		Assert.assertEquals(123, foo.getFoo());
	}

	@Test
	public void testLoadContextFromJava() {
		ContextExample example = new ContextExample();

		ApplicationContext context = example.loadContextFromJava();
		Foo foo = (Foo) context.getBean("foo");
		Assert.assertEquals(456, foo.getFoo());
	}
}
