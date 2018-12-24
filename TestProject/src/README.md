# CHAPTER12. 지네릭스

### 1.1 지네릭스란?

: 객체의 타입을 컴파일 시에 체크하는 기능


 1. 장점
    - 타입의 안정성을 제공
    - 타입체크와 형변환을 생략할 수 있으므로 코드가 간결함 

     다룰 객체의 타입을 미리 명시해줌으로써 번거로운 형변환을 줄여준다


### 1.2 지네릭 클래스 선언

1. 클래스에 선언하는 타입
<ex>

```
class Box<T> {
    T item;

    void setItem(T item) { this.item = item;}
    T getItem() { return item;}

    }
```
- 클래스 옆에< T> 를 붙이면 됨
- 'Object'를 모두 'T'로 바꿈
- 상황에 맞게 선택해서 사용

    - 'T'를 타입 변수라고 하며 Type의 T 사용
    - ArrayList< E>는 Element의 E 사용
    - Map<K,V>는 Key의 K, Value의 V 
>Box 클래스의 객체를 생성 시 참조변수와 생성자에 T대신 실제 타입을 지정

```
Box<String> b = new Box<String>(); //타입 T 대신 실제타입을 지정
b.setItem(new Object());           //에러, String이외의 타입 지정 불가
b.setItem("ABC") ;                 // OK
String item = b.getItem();         //형변환 필요없음

```
<ex>

```
class Box<String> {
    String item;

    void setItem(String item) { this.item = item;}
    String getItem() { return item;}

    }
```

지네릭 타입을 지정하지 않을 경우 안전하지 않다는 경고 메시지가 뜬다

#### 지네릭스의 용어

```
                      class Box<T> {}


-Box<T>:지네릭 클래스
-T :타입변수 또는 타입 매개변수
-Box: 원시 타입(raw type)
```

```
             Box<String> b = new Box<String>(); 


 -Box<String>:지네릭 타입 호출(타입 매개변수에 타입을 지정하는 것)
 -String:대입된 타입          
```
#### 지네릭스의 제한
 지네릭스는 인스턴스별로 다르게 동작하도록 하기 위함

Box< Apple> appleBox = new Box< Apple> () ; //Apple 객체만 저장가능
Box< Grape> grapeBox = new Box< Grape> () ; //Grape 객체만 저장가능
    
1. 모든 객체에 동일하게 동작하는 static멤버에 타입변수 T 사용 못한다
   - T가 인스턴스변수로 간주됨

   - 대입된 타입의 종류에 관계없이 동일해야 하기 때문에

2. 배열생성도 허용되지 않는다

   - T[] toArray(){
    T[] tmpArr = new T[itemArr.lenth];
}
   - new연산자는 컴파일 시점에 타입T가 무엇인지 알아야함
   - 하지만 Box<T>클래스를 컴파일하는 시점엔 알 수 없다
 - 대신 'Reflection API'의 newInstance()와 같이 동적으로 객체 생성하는 메서드로 배열생성     
 -  Object열을 생성, 복사 T[]로 형변환
  
### 1.3 지네릭 클래스의 객체 생성
1. 참조변수와 생성자에 대입된 타입이 일치해야 한다

     Box< Apple> appleBox = new Box< Grape>();      // 에러

2. 상속관계도 마찬가지. Apple이 Fruit의 자손일 경우

    Box< Fruit> appleBox = new Box< Apple> () ; //에러, 대입된 타입이 다르다
3. 지네릭 클래스의 타입이 상속관계, 대입타입이 같은 경우(FruitBox는 Box의 자손)

     Box< Apple> appleBox = new FruitBox< Apple>(); // OK. 다형성
4. 대입타입과 다른타입의 객체는 추가 불가능

     Box< Apple> appleBox = new Box< Apple> ();
     
      appleBox. add (new Apple () ) ; //OK.
      
     appleBox.add (new Grape () ) ; / / 에러 . Box<Apple>에는 Apple객체만 추가가능

   >  <예제12-1>

### 1.4 제한된 지네릭 클래스
>매개변수 T에 지정할 수 있는 타입의 종류를 제한하는 방법
- 'extends'로 특정타입 자손만 제한
```
class FruitBox<T extends Fruit> { // Fruit의 자손만 타입으로 지정가능 
ArrayList<T> list = new ArrayList<T> () ;

FruitBox<Apple> appleBox = new FruitBox<Apple> ();    // OK 
FruitBox<Toy> toyBox = new FruitBox<Toy> () ;  // 에러. Toy는 Fruit의 자손이 아님

//add()의 매개변수 타입T도 자손이므로 

FruitBox<Fruit> fruitBox = new FruitBox<Fruit> ();
 fruitBox. add (new Apple () ) ;         //OK. Apple 이 Fruit 의 자손 
 fruitBox.add(new Grape());             // OK. Grape가 Fruit의 자손


```

다형성에서 조상타입의 참조변수로 자손타입 객체를 가리킨 것 처럼, T에 Object를 대입하면 모든 종류의 객체를 저장할 수 있다

- 인터페이스를 구현해야 한다면 'implements'가 아니라 'extends'를 사용
- Fruit의 자손이면서 Eatable인터페이스도 구현해야한다면 '&'으로 연결

  class FruitBox<T extends Fruit & Eatable>

><예제12-2에러부분>

### 1.5 와일드 카드


```
class Juicer { 
    static Juice makeJuice (FruitBox<Fruit> box) {     // <Fruit>으로 지정 
    String tmp = "" ; 
    for (Fruit f : box.getList () ) tmp += f + " ";
    return new Juice(tmp);
    }
}
```
>jucier은 지네릭 클래스가 아니며, static메서드에는 T를 사용할 수 없으므로 지네릭스 적용하지 않던가, Fruit과 같이 특정 타입을 지정해야함

```
FruitBox<Fruit> fruitBox = new FruitBox<Fruit> (); 
FruitBox<Apple> appleBox = new FruitBox<Apple> ();

System.out.println(Juicer.makeJuice(fruitBox)); // OK. FruitBox<Fruit>
System.out.println(Juicer.makeJuice(appleBox)); // 에러. FruitBox<Apple>

```

>FruitBox< Fruit>으로 고정해서 에러발생

>여러 가지 타입의 매개변수를 갖는 makeJuice()생성

    static Juice makeJuice (FruitBox< Apple> box) {}
    
> '메서드 중복 정의'로 인해 컴파일 에러가 발생한다. 이때 '와일드 카드' 사용

- 와일드 카드 표현
   - < ? extends T> 와일드 카드의 상한 제한. T와 그 자손들만 가능
   - < ? super T> 와일드 카드의 하한 제한. T와 그 조상들만 가능
   - < ?> 제한 없음. 모든 타입이 가능.
 < ? extends Object〉와 동일  

>> 와일드 카드를 사용한 결과
 ```
static Juice makeJuice(FruitBox<? extends Fruit> box) {
    String tmp = "";
    for(Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
} 

 ```
 > FruitBox< fruit>뿐만 아니라, Fruit< Apple>도 가능하게 된다.

      System.out.printIn(Juicer.makeJuice(appleBox)); // OK. FruitBox<Apple>

> <예제들12->

### 1.6 지네릭 메서드
: 메서드의 선언부에 지네릭 타입이 선언된 메서드
: 반환타입 바로 앞에 지네릭 타입 선언
 - Collections.sort()

        static <T> void sort(List<T> list, Comparator<? super T> c)
- 지네릭 클래스에 정의된 타입 매개변수와 지네릭 메서드에 정의된 타입 매개변수는 전혀 별개의 것이다
- 
           class FruitBox<T> {
               ...
           static <T> void sort(List<T> list, Comparator<? super T> c) {
               ...
           }
       }

> 지네릭 메서드는 지네릭 클래스가 아닌 클래스에도 정의될 수 있다

> FruitBox의 매개변수 T와 sort()의 매개변수 T는 타입문자만 같을 뿐 다르다
>>  static멤버에는 타입 매개 변수를 사용할 수 없지만，메서드에 지네릭 타입을 선언하고 사용하는 것은 가능 

-지역변수와 비슷하다. 메서드 내에서만 지역적으로 사용됨


> 지네릭 메서드로 변경

       static <T extends Fruit> Juice makeJuice(FruitBox<T> box) { 
           String tmp = "";
           for(Fruit f : box.getList()) tmp += f +" ";
           return new Juice(tmp);
       }

       FruitBox<Fruit> fruitBox = new FruitBox<Fruit> (); 
       FruitBox<Apple> appleBox = new FruitBox<Apple> ();

       System.out.println(Juicer.<Fruit>makeJuice(fruitBox)); //타입 생략 가능
       System.out.println(Juicer.<Apple>makeJuice(appleBox)); //타입 생략 가능

       System.out.println (<Fruit>makeJuice (fruitBox)) ; //에러.클래스이름 생략불가 

```