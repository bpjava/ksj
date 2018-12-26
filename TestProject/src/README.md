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
```
       static <T extends Fruit> Juice makeJuice(FruitBox<T> box) { 
           String tmp = "";
           for(Fruit f : box.getList()) tmp += f +" ";
           return new Juice(tmp);
       }

       FruitBox<Fruit> fruitBox = new FruitBox<Fruit> ();  //타입 변수에 타입을 대입
       FruitBox<Apple> appleBox = new FruitBox<Apple> ();

       System.out.println(Juicer.<Fruit>makeJuice(fruitBox)); //타입 생략 가능
       System.out.println(Juicer.<Apple>makeJuice(appleBox)); //타입 생략 가능
```
> fruitBox, appleBox의 선언부를 통해 컴파일러가 타입을 추정할 수 있기 때문에 생략해도 된다
```
   System.out.println(this.<Fruit>makeJuice(fruitBox)); //OK 
   System.out.println(<Fruit>makeJuice (fruitBox)) ; //에러.클래스이름 생략불가 
```
> 대입된 타입을 생략할 수 없는 경우엔 참조변수(this)나 클래스 이름을 생략할 수 없다

- 매개변수의 타입이 복잡할 때도 유용하다
> Collections 클래스의 sort()
> Comparable인터페이스를 구현한다는 것이지만 implements 사용안함

       public static <T extends Comparable<? super T>> void sort (List<T> list)
       
 1. List< T> :타입 T를 요소로 하는 List를 매개변수로 허용한다. 
2. < T extends Comparable>:'T'는 Comparable을 구현한 클래스    
3. Comparable<? super T>:'T'또는 그 조상의 타입을 비교하는 Comparable

### 1.7 지네릭 타입의 형변환
>지네릭 타입과 원시 타입의 형변환이 가능?
가능하지만 경고 발생

      Box box = null;
      Box<Object> objBox = null; 
      
      box = (Box) objBox;     // OK. 지네릭 타입 -> 원시 타입 . 경고 발생
      objBox = (Box<Object>)box;     // OK. 원시 타입 ―> 지네릭 타입. 경고 발생

 
 대입된 타입이 다른 지네릭 타입 간 변환도 불가능

 >Box< String>이 Box<? extends Object>로 형변환 가능?
 가능하다

        static Juice makeJuice(FruitBox<? extends Fruit> box){ ...}

        FruitBox<? extends Fruit>box = new FruitBox<Fruit>();     //OK
        FruitBox<? extends Fruit>box = new FruitBox<Apple>();     //OK


>형변환 순서

    public final class Optional<T> {
        private static final Optional<?> EMPTY = new Optional<> ();
        private final T value; 
        ...
        public static<T> Optional<T> empty() {
             Optional<T> t = (Optional<T>) EMPTY; 
             return t;
              }
        ...
    }

상수 EMPTY에 비어있는 Optional객체를 생성해서 저장했다가 empty()를 호출하면 형변환해서 반환

    
       Optional<?> EMPTY = new Optional<>();
        -> Optional<? extends Object〉 EMPTY = new Optional<> () ;
        -> Optional<? extends Object〉 EMPTY = new Optional<Object> () ;
    
- < ?>는 <? extends Object> 줄여서 쓴것
- < > 안에 Object가 생략된것

>> Optional< Object>가 아닌 Optional<?>로 한 이유?
Optional< T>로 형변환이 가능

    Optional<?>      wopt = new Optional<Object> () ;
    Optional<Object〉 oopt = new Optional<Object> () ;

    Optional<String> sopt = (Optional<String>) wopt; // OK.형변환 가능 
    Optional<String> sopt = (Optional<String>) oopt; // 에러.형변환 불가

> 와일드 카드가 사용된 지네릭 타입끼리 형변환가능하지만 경고
    
    FruitBox<? extends Object〉 objBox = null;
    FruitBox<? extends String〉 strBox = null;
    
    strBox = (FruitBox<? extends String>) objBox; // OK. 미확정 타입으로 형변환 경고 
    objBox = (FruitBox<? extends Object>) strBox; // OK. 미확정 타입으로 형변환 경고

### 1.8 지네릭 타입의 제거
: 컴파일러는 지네릭 타입을 이용해서 소스파일 체크하고, 형변환을 넣어준 후 제거한다

(지네릭이 도입되기 이전 소스 코드와의 호환성 유지를 위해)

- 지네릭 타입의 경계를 제거

  
         
          class Box<T extends Fruit> { 
              void add (T t) {
              }
           }
  - T는 Fruit로 치환, 클래스 옆의 선언 제거

         class Box {
              void add(Fruit t) {
              }
         }
 - 지네릭 타입 제거 후 타입이 일치하지 않으면, 형변환 추가
        
 ## 2. 열거형
### 2.1 열거형이란?


      class Card {
           enum Kind { CLOVER, HEART, DIAMOND, SPADE } // 열거형 Kind를 정의
           enum Value { TWO, THREE, FOUR } // 열거형 Value를 정의 
           
           final Kind kind; // 타입이 int가 아닌 Kind임에 유의하자.
           final Value value; 
      }

### 2.2 열거형의 정의와 사용
> 열거형 정의 

       enum 열거형이름 { 상수명1, 상수명2, ... }
       
- 열거형에 정의된 상수 '열거형이름.상수명'

        enum Direction { EAST, SOUTH, WEST, NORTH }

        class Unit { 
            int x, y; 
            Direction dir;  // 열거형을 인스턴스 변수로 선언
            
            void init () { dir = Direction.EAST;   // 유닛의 방향을 EAST로 초기화 
            
            }
        }



- 열거형 상수간의 비교 '=='
    
    - 빠른 성능을 제공한다 
    
    - '<', '>'와 같은 비교연산자는 사용불가

    - compareTo() 사용가능
    (비교대상이 같으면 0, 왼쪽이 크면 양수, 오른쪽이 크면 음수)
- switch문의 조건식에도 사용가능

        void move () { 
            switch(dir) { 
                case EAST: x++; // Direction.EAST라고 쓰면 안된다.
                 break; 
                case WEST: x—— ;
                 break;
            }
        }
   열거형의 이름은 빼고 상수 이름만 적어야 함     
- 모든 열거형의 조상 -java.lang.Enum

  -열거형 Direction에 정의된 모든 상수를 출력

       Direction[] dArr = Direction.values();
       
       for(Direction d : dArr)   // for(Direction d : Direction.values())
          System.out.printf("%s=%d%n", d.name(),d.ordinal();)

    values(): 모든 상수를 배열에 담아 반환

    ordinal(): java.lang.Enum클래스에 정의된 것      

> Enum클래스에 정의된 메서드

|메서드  | 설명|
|:--:|:--:|
|Class< E> getDeclaringClass|열거형의 Class객체를 반환| 
|String name()|열거형 상수의 이름을 문자열로 반환 |
|int ordinal|열거형 상수가 정의된 순서를 반환(0부터 시작)|
|T valueOf(Class<T> enumType, String name)|지정된 열거형에서 name과 일치하는 열거형 상수를 반환 |

<예제12-5>

### 2.3 열거형에 멤버 추가하기

- 열거형 상수의 값이 불규칙한 경우 값을 () 와 함께 적음
- 지정된 값을 저장할 수 있는 인스턴스 변수와 생성자를 새로 추가
- 열거형 상수를 먼저 정의한 후 추가

      enum Direction { 
          EAST (1) , SOUTH (5), WEST (-1), NORTH (10) ；  // 끝에 ';' 를 추가해야 한다.
          
          private final int value;  // 정수를 저장할 필드 (인스턴스 변수) 를 추가 
          Direction (int value) { this.value = value; } // 생성자를 추가

          public int getValue() {return value; } 
      }

#### 추상 메서드 추가하기
참고)

        enum Transportation {
             BUS(100) { int fare(int distance) { return distance*BASIC_FARE;}}, 
             TRAIN(150) { int fare(int distance) { return distance*BASIC _FARE; }}, 
             SHIP(100) { int fare(int distance) { return distance*BASIC_FARE; }},
             AIRPLANE(300) { int fare(int distance) { return distance*BASIC_FARE; }}；

             abstract int fare (int distance) ; // 거리에 따른 요금을 계산하는 추상 메서드 
             protected final int BASIC_FARE; // protected로 해야 각 상수에서 접근가능 

### 2.4 열거형의 이해

      enum Direction {EAST, SOUTH, WEST, NORTH}

-클래스로 정의

      class Direction { 
          static final Direction EAST = new Direction ("EAST”) ;
          static final Direction SOUTH = new Direction("SOUTH");
          static final Direction WEST = new Direction("WEST”);
          static final Direction NORTH = new Direction ("NORTH") ; 

          private String name;
          private Direction(String name) { this.name = name; }
      }
  -static 상수 EAST, SOUTH, WEST, NORTH의 값은 객체의 주소(변하지않으므로 '==' 가능) 

  <ordinal예제12-8>   

  ## 3.애너테이션
  ### 3.1 애너테이션이란?
: 소스코드 안에 다른 프로그램을 위한 정보를 지정된 형식으로 포함시킨 것

- 주석 /** ~ */에 소스코드에 대한 설명을 넣고, @ 태그로 주석안에 정보를 저장

- 장점: 프로그래밍 언어에 영향 없으면서 정보를 제공

#### 특정메서드만 테스트 @Test

@Test //이 메서드가 테스트 대상임을 알림  
public void method(){
    ...
}

### 3.2 표준 애너테이션

> 기본적으로 제공하는 표준 애너테이션(*은 메타 애너테이션)

|애너테이션  | 설명|
|:--:|:--:|
|@Override|오버라이딩하는 메서드|
|@Deprecated|사용하지 않도록 권장하는 대상|  
|@SuppressWarnings|특정 경고메시지가 나타나지 않게 함| 
|@SafeVarargs|지네릭스 타입의 가변인자| 
|@FunctionalInterface|함수형 인터페이스라고 알림| 
|@Native|native메서드에서 참조되는 상수 앞| 
|@Target*|애너테이션이 적용가능한 대상 지정| 
|@Documented*|애너테이션 정보가 javadoc으로 작성된 문서에 포함|
|@Inherited*|애너테이션이 자손 클래스에 상속되도록|
|@Retention*|애너테이션이 유지되는 범위 지정|
|@Repeatable* |애너테이션을 반복해서 적용| 

#### @Override
: 조상의 메서드를 오버라이딩하는 것을 컴파일러에 알림

메서드 앞에만 붙임
- 오버라이딩할 때 메서드의 이름을 잘못 적을 시 지나치는 오류를 방지
- 조상에 있는지 확인하고 없으면, 에러메시지를 출력 

       class Parent { 
           void parentMethod() { } 
           }
       class Child extends Parent {
            @Override
            void parentmethod() { } // 조상 메서드의 이름을 잘못 적었음.


-일반적으로 새로운 이름의 메서드가 추가된 것으로 인식

-오류가 발생 하지 않고 조상의 메서드가 호출

  #### @Deprecated
: 더이상 사용되지 않는 필드나 메서드를 알림

- 기존의 것 대신 새로 추가된 개선된 기능을 사용하도록 유도

> getDate()보단 get() 사용하도록 유도

           int getDate () 
           Deprecated. 
           As of JDK version 1.1, replaced by Calendar. get (Calendar. DAY_OF_MONTH).
  -권할 뿐 강제성은 없기 때문에 컴파일 실행됨
  
  >사용 시 메시지가 나타남

          Note : AnnotationEx2.j ava uses or overrides a deprecated API. 
          Note : Recompile with -XIint:deprecation for details.

 #### @FunctionalInterface
: 함수형 인터페이스를 선언할때 알림

- 함수형 인터페이스를 올바르게 선언 했는지 확인, 잘못된 경우 에러 발생
- 실수 방지
 
          @FunctionalInterface 
          public interface Runnable {
               public abstract void run () ; // 추상 에서드 }

 #### @SuppressWarnings
: 컴파일러가 보여주는 경고메시지가 나타나지 않게 함

- 경우에 따라  묵인해야하는 경고가 발생하는 대상에 붙임
- 억제할 수 있는 경고 메시지 
  - deprecation : @Deprecated를 사용해서 발생
  - unchecked : 지네릭스로 타입을 미지정했을 때 발생
  - rawtypes : 지네릭스를 사용하지 않아서 발생
  - varargs : 가변인자의 타입이 지네릭 타입일 때 발생

        @SuppressWarnings ("unchecked")     // 지네릭스와 관련된 경고를 억제
        ArrayList list = new ArrayList () ; // 지네릭 타입을 지정하지 않았음
        list.add (obj) ;                   // 여기서 경고가 발생

<예제 12-11>

 #### @SafeVarargs
: 코드에 문제가 없다면 이경고를 억제하기 위해 사용

- 가변인자의 타입이 non-reifiable타입일 경우 'unchecked'경고 발생
  -  reifiable타입: 컴파일 후에도 제거되지 않는 타입
  -  non-reifiable타입 : 제거되는 타입(지네릭 타입들은 대부분)
- 생성자, static, final이 붙은 메서드에만 사용가능
> java.util.Arrays의 asList()

          public static <T> List<T> asList(T ... a) {
               return new ArrayList<T> (a) ; // ArrayList (E [］ array) 를 호출.경고발생 
          }
-asList()의 매개변수가 가변인자이며 지네릭 타입

-타입T는 컴파일 과정에서 Object로 바뀜         

-Object[] 에는 모든 타입의 객체가 들어올 가능성 있으므로 위험하다고 경고 메시지
- @SuppressWarnings(’’unchecked”)사용 시, 메서드 선언뿐만 아니라 두 메서드가 호출되는 곳에도 넣아야 함
- ‘varargs’경고는 억제할 수 없다

          @SafeVarargs                       // 'unchecked'경고를 억제한다
          @SuppressWarnings ("varargs")      // 'varargs'경고를 억제한다.
          public static <T> List<T> asList(T... a) {
                return new ArrayList<>(a) ;
                 }

### 3.3 메타 애너테이션
: 애너테이션에 붙이는 애너테이션

애너테이션의 적용대상이나 유지기간 등을 지정

#### @Target
: 애너테이션이 적용가능한 대상을 지정
> @SuppressWarnings에 적용할 수 있는 대상

         @Target({TYPE, FIELD, METHOD, PARAMETER,CONSTRUCTOR, LOCAL_VARIABLE})
         @Retention(RetentionPolicy.SOURCE) 
         public @interface SuppressWarnings { 
             String[] value();
         }
> 적용대상 종류

|대상 타입  | 의미|
|:--:|:--:|
|ANNOTATION_TYPE|애너테이션|
|CONSTRUCTOR|생성자|
|FIELD|필드(멤버면수,enum상수)|
|LOCAL_VARIABLE|지역변수|
|METHOD|메서드|
|PACKAGE|패키지|
|PARAMETER|매개변수|
|TYPE|타입(클래스,인터페이스,enum)|
|TYPE_PARAMETER|타입 매개변수|
|TYPE_USE|타입이 사용되는 모든 곳|

#### @Retention
: 애너테이션이 유지(retention)되는 기간을 지정
> 유지정책의 종류

|유지 정책 | 의미|
|:--:|:--:|
|SOURCE|소스 파일에만 존재. 클래스파일에는 존재하지 않음|
|CLASS|클래스 파일에 존재. 실행시에 사용불가. 기본값|
|RUNTIME|클래스 파일에 존재. 실행시에 사용가능|

- '@Override'나 '@SuppressWarnings’처럼 컴파일러에 의해 사용되는 애너테이션은 유지 정책이 'SOURCE'
    
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.SOURCE)
        public ©interface Override { }

- @FunctionalInterface 는 실행시에도 사용되므로 'Runtime'

        @Documented
        @Retention(RetentionPolicy.RUNTIME) 
        @Target(ElementType.TYPE) 
        public @interface Functionallnterface { }

#### @Documented
: 애너테이션 정보가 javadoc으로 작성한 문서에 포함되도록 함
- '@Override'와 '@SuppressWarnings’를 제외하고 모두 붙어 있다
> 위 코드 참고

#### @Inherited
: 애너테이션이 자손 클래스에 상속되도록 함
- 조상클래스에 붙이면, 자손 클래스도 이 애너테이션이 붙은 것 같이 인식된다

          ©Inherited       // @SupperAnno가 자손까지 영향 미치게
          @interface SupperAnno { } 
          
          @SuperAnno 
          class Parent { }

          class Child extends Parent { }    // Child에 애너테이션이 안 붙었지만, 붙은 것으로 인식

#### @Repeatable
: 보통 대상 하나에 한 종류 애너테이션 붙이지만
이를 통해 여러번 붙일 수 있음

         @interface ToDos {         // 여러 개의 ToDo애너테이션을 담을 컨테이너 애너테이션 
            ToDo[] value () ;       // ToDo애너테이션 배열타입의 요소를 선언.이름이 반드시 value이어야 함
         }

         @Repeatable (ToDos.class)  // 괄호 안에 컨테이너 애너테이션을 지정해 줘야한다.
         @interface ToDo { 
             String value () ;
         }
         @ToDo ("delete test codes ")
         @ToDo ("override inherited methods")
          class MyClass {
              ...
           }

#### @Native
: 네이티브 메서드에 의해 참조되는 상수 필드에 붙이는 애너테이션

### 3.4 애너테이션 타입 정의

