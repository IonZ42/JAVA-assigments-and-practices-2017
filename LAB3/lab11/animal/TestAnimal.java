import java.util.*;
import java.io.*;

class TestAnimal{
	public static void main(String[] args) {
		System.out.println("\nPet[] pets= {new Cat(\"NyaKo\"),new Fish(\"pi\")};//a:interface as a data type,�ӿڱ���");
		Pet[] pets= {new Cat("NyaKo"),new Fish("pi")};//a:interface as a data type,�ӿڱ���

		System.out.println("\nfor(Pet p:pets) {p.play();}//a:interface as a data type,�ӿڱ���  + g:using dynamic binding,�ö�̬��"
				+"\nprint:");
		for(Pet p:pets) {p.play();}//a:interface as a data type + g:using dynamic binding,�ö�̬��
		
		System.out.println("\nAnimal[] animals={new Spider(),new Cat(),new Fish()};/*b:abstract class as a data type,��������� \r\n" + 
				"+ c:using super to call super class methods,����ʱ����super���� \r\n" + 
				"+ f:using polymorphism,��̬*/");
		Animal[] animals={new Spider(),new Cat(),new Fish()};/*b:abstract class as a data type,���������
		+ c:using super to call super class methods,����ʱ����super���� 
		+ f:using polymorphism,��̬*/

		System.out.println("\nfor(Animal a:animals) {a.walk();}//g:using dynamic binding,�ö�̬��"
				+ "\nprint:");
		for(Animal a:animals) {a.walk();}//g:using dynamic binding,�ö�̬��
		
		System.out.println("\nanimals[1].eat();//d:implicit casting objects,��ʽcast"
				+"\nprint:");
		animals[1].eat();//d:implicit casting objects,��ʽcast
		
		System.out.println("\n((Cat)animals[1]).eat();//e:explicit casting objects,��ʽcast"
				+ "\nprint:");
		((Cat)animals[1]).eat();//e:explicit casting objects,��ʽcast

		System.out.println("\nnew Generic<Cat>().print(new Cat());//h:generic programming,ʹ�÷���"
				+ "\nprint:");
		new Generic<Cat>().print(new Cat());//h:generic programming,ʹ�÷���
	}
}

abstract class Animal {
	protected int legs;
	
	protected Animal(int leg) {legs=leg;}
	public void walk() {}
	public abstract void eat();
}

interface Pet{
	String getName();
	void setName(String n);
	void play();
}

class Generic <T>{
	public Generic() {}
	public void print(T t) {
	if(t instanceof Animal) {((Animal) t).walk();}}
}

class Spider extends Animal{
	public Spider() {super(8);}//c��using super to call super class methods,��super.���ú���
	
	@Override
	public void eat() {System.out.println("Spiders eat juicy bugs");}
	@Override
	public void walk() {System.out.println("Spiders strik with 8 legs");}
}

class Cat extends Animal implements Pet {
	private String name;

	public Cat(String n) {super(4);name=n;}//c��using super to call super class methods,��super.���ú���
	public Cat() {this("Ketty(default)");}
	@Override
	public String getName() {return name;}
	@Override
	public void setName(String n) {name=n;}
	@Override
	public void play() {System.out.println("The pet cat "+name+" play with toys");}
	@Override
	public void eat() {System.out.println("Cats eat delicious food");}
	@Override
	public void walk() {System.out.println("Cats pace with 4 legs");}
}

class Fish extends Animal implements Pet{
	private String name;
	
	public Fish(String n) {super(0);name=n;}//c��using super to call super class methods,��super.���ú���
	public Fish() {this("Bubble(default)");}
	@Override
	public String getName() {return name;}
	@Override
	public void setName(String n) {name=n;}
	@Override
	public void play() {System.out.println("The pet fish "+name+" play water");}
	@Override
	public void walk() {System.out.println("Fish have no leg, they swim");}
	@Override
	public void eat() {System.out.println("Fish eat bacteria and seaweeds");}
}