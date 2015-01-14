public class Delegation {
	public static void main(String args[]) {
		C111 c111 = new C111();
		System.out.println(c111.m111());

		C112 c112 = new C112();
		System.out.println(c112.m112());

		D111 d111 = new D111();
		System.out.println(d111.m111());

		D112 d112 = new D112();
		System.out.println(d112.m112());
	}
}

class C1 {
	int a1 = 1;

	public int m1() {
		return a1 + p1(100) + q1(100);
	}

	public int p1(int m) {
		return m;
	}

	public int q1(int m) {
		return m;
	}
}

class C11 extends C1 {
	int a11 = 11;

	public int m11() {
		return m1() + q1(200);
	}

	public int p1(int m) {
		return m * a1;
	}

	public int q1(int m) {
		return m + a11;
	}
}

class C111 extends C11 {
	int a111 = 111;

	public int m111() {
		return m1() + m11() + a111;
	}

	public int p1(int m) {
		return m * a1 * a11;
	}
}

class C112 extends C11 {
	int a112 = 112;

	public int m112() {
		return m1() + m11() + a112;
	}

	public int p1(int m) {
		return m * a1 * a11 * a112;

	}
}


// -------SIMULATING CLASS INHERITANCE BY DELEGATION ---------

interface I1 {
	int m1();
	int p1(int m);
	int q1(int m);
	int getA1();
}

interface I11 extends I1 {
	int m11();
	int getA11();
}

interface I111 extends I11 {
	int m111();
}

interface I112 extends I11 {
	int m112();
}

class D1 implements I1 {

	int a1 = 1;
	I1 d11;
	I1 d111;
	public D1(I1 i1, I1 i2){
		d11 = i1;
		d111 = i2;
	}
	public int m1() {
		return a1 + p1(100) + q1(100);
	}
	public int p1(int m) {
		return d111.p1(m);
	}
	public int q1(int m) {
		return d11.q1(m);
	}
	public int getA1() {
		return a1;
	}
}

class D11 implements I11 {

	int a11;
	I1 d1;

	public D11(I1 i1){
		a11 = 11;
		d1 = new D1(this,i1);
	}
	public int m1(){
		return d1.m1();
	}
	public int m11() {
		return m1() + q1(200);
	}
	public int p1(int m){
		return m;
	}
	public int q1(int m) {
		return m + a11;
	}
	public int getA11() {
		return a11;
	}
	public int getA1() {
		return d1.getA1();
	}	
}

class D111 implements I111 {

	int a111;
	I11 d11;
	public D111(){
		a111 = 111;
		d11 = new D11(this);
	}
	public int m1() {
		return d11.m1();
	}
	public int m11() {
		return d11.m11();
	}
	public int m111() {
		return m1() + m11() + a111;
	}	
	public int p1(int m) {
		return m * getA1() * getA11();
	}	
	public int q1(int m) {
		return d11.q1(m);   
	}	
	public int getA1() {
		return d11.getA1();
	}	
	public int getA11() {
		return d11.getA11();
	}
}

class D112 implements I112 {
	I11 d11;
	int a112 = 112;
	public D112(){
		a112 = 112;
		d11 = new D11(this);
	}
	public int m1() {
		return d11.m1();
	}
	public int m11() {
		return d11.m11();
	}
	public int m112() {
		return m1() + m11() + a112;
	}
	public int p1(int m) {
		return m * getA1() * getA11() * a112;	
	}
	public int q1(int m) {
		return d11.q1(m);
	}
	public int getA11() {
		return d11.getA11();
	}
	public int getA1() {
		return d11.getA1();
	} 
}
