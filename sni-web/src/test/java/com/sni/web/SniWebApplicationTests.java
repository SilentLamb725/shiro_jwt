package com.sni.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SniWebApplicationTests {

	@Test
	public void contextLoads() {
	}

	public static void main(String[] args) {
		MathOperate plusOperate = Math::addExact;
		System.out.println(plusOperate.operate(1,4));

		MathOperate minusOperate = Math::subtractExact;
		System.out.println(minusOperate.operate(4 , 9));

		MathOperate absOperate = (a, b) -> 66;


		int result = absOperate.operate(15, 9);
		System.out.println(result);

		new Thread(() -> {
			System.out.println("werwer");
		});
	}

	interface MathOperate {
		int operate(int a, int b);
	}

	interface A {
		String hi();

		String greet();

		default void hello() {
			System.out.println("A.hello");
		}
	}

	public interface B {

		String hi();

		String hh();

		default void hello() {
			System.out.println("B.hello");
		}

	}

	public class D {

		public void hello() {
			System.out.println("D.hello");
		}
	}

	public class C extends D implements A, B{
		@Override
		public String hi() {
			return null;
		}

		@Override
		public String greet() {
			return null;
		}

		@Override
		public String hh() {
			return null;
		}

//		@Override
//		public void hello() {
//			System.out.println("C.hello");
//		}
	}
}
