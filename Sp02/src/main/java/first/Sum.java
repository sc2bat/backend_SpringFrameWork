package first;

public class Sum implements Calculator {

	@Override
	public int cal(int firstNum, int secondNum) {
		// TODO Auto-generated method stub
		int result = 0;
		result = firstNum + secondNum;
		
		return result;
	}
	
}
