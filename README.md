## 15. 입출력

### 1.1 입출력이란?
컴퓨터 내부 또는 외부의 장치와 프로그램간의 데이터를 주고받는 것

> System.out.println()

### 1.2 스트림(stream)
두 대상을 연결하고 데이터를 전송할 수 있는 역할
> 연결통로 역할

> 물이 한쪽 방향으로만 흐르는 것처럼 스트림도 단방향통신만 가능
- 하나의 스트림으로 입력과 출력 동시에 불가
- 입력스트림과 출력스트림 2개 필요 
  - 스트림은 먼저 보낸 데이터를 먼저 받음(FIFO)
  - 중간에 건너뜀 없이 연속적으로 주고 받음 

### 1.3 바이트기반 스트림- InputStream, OutputStream

> 입력스트림과 출력스트림의 종류

|입력스트림 | 출력스트림|입출력 대상의 종류
|:--:|:--:|:--:|
|FileInputStream|FileOutputStream|파일 
|ByteArrayInputStream|ByteArrayOutputStream|메모리(byte배열)
|PipedInputStream|PipedOutputStream|프로세스(프로세스간의 통신)|
|AudioInputStream|AudioOutputStream|오디오장치|

> InputStream과 OutputStream에 정의된 읽기와 쓰기를 수행하는 메서드

|InputStream | OutputStream
|:--:|:--:|
|abstract int read()|abstract void write(int b)|
|int read(byte[] b)|void write(byte[] b)|
|int read(byte[] b,int off, int len)|void write(byte[] b, int off, int len)|

     public abstract class InputStream{
         ...
         // 입력스트림으로 부터 1byte를 읽어서 반환한다.읽을 수 없으면 -1을 반환
         abstract int read();
         // 입력스트림으로부터 len개의 byte를 읽어서 byte배열 b의 off위치부터 저장한다

         int read(byte[] b, int off, int len){
             ...
             for(int=off; i < off+len; i++){
                 //read()를 호출해서 데이터를 읽어서 배열을 채운다.
                 b[i] = (byte)read();     
            } 
                 ...
         }
         //입력스트림으로부터 byte배열 b의 크기만큼 데이터를 읽어서 배열 b에 저장
         int read(byte[] b){
             return read(b, 0, b.length);
         }


1. read(byte[] b, int off, int len)는 read()를 호출
2. read(byte[] b)도 read(byte[] b, int off, int len)를 호출 
3. read(byte[] b, int off, int len)가 다시 read()호출하기 때문에  read(byte[] b)도 추상메서드 read()를 호출
4. 결론은 read() 없이는  read(byte[] b, int off, int len)와  read(byte[] b) 의미 없다

### 1.4 보조 스트림


     












