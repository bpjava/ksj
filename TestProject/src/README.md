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





