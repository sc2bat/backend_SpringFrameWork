package first;

public class MainClass {
	public static void main(String[] args) {
		System.out.println("스프링 프레임워크 응용 소프트웨어 프로젝트입니다");
		
		Calculator a = new Sum();
		int result = a.cal(23, 33);
		System.out.println(result);
		
		Calculator b = new Sub();
		result = b.cal(23, 20);
		System.out.println(result);
		
		Calculator c = new Mul();
		result = c.cal(23, 2);
		System.out.println(result);
		
	}
}
