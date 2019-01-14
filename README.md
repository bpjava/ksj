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

 실제 데이터를 주고받는 스트림이 아니기 때문에 데이터를 입출력할 수 있는 기능은 없다

- 스트림의 기능을 향상, 새로운 기능 추가

- 스트림 먼저 생성 후 보조스트림 생성
>  test.txt 파일을 읽기 위해 FileInputStream을 사용하고, 보조스트림은 BufferedInputStream 사용

       //먼저 기반스트림을 생성
       FileInputStream fis = new FileInputStream("text.txt");

       //기반스트림을 이용해서 보조스트림 생성
       BufferedInputStream bis = new BufferedInputStream(fis);

       bis.read();   //보조스트림으로부터 데이터를 읽음


> 보조스트림의 종류

|입력| 출력| 설명
|:--:|:--:|:--:|
|FilterInputStream|FilterOutputStream|필터를 이용한 입출력 처리
|BufferedInputStream|BufferedOutputStream|버퍼를 이용한 입출력 성능향상
|DataInputStream|DataOutputStream|int, float와 같은 기본형 단위로 데이터를 처리
|SequenceInputStream|없음| 두 개의 스트림을 하나로 연결
|LineNumberInputStream|없음|읽어 온 데이터의 라인 번호를 카운트
|ObjectInputStream| ObjectOutputStream|데이터를 객체단위로 읽고 쓰는데 사용. 객체 직렬화와 관련
|없음| PrintStream|버퍼를 이용하며 추가적인 print관련 기능있음
|PushbackInputStream|없음|버퍼를 이용해서 읽어 온 데이터를 다시 되돌림

### 1.5 문자기반 스트림 - Reader, Writer

이전의 스트림은 바이트기반(입출력 단위가 1 byte) 스트림이었다

java  char는 2 byte이기 때문에 어려움

이를 보완하기 위해서 문자기반 스트림 제공

      InputStream = Reader

      OutputStream = Writer

> 바이트기반 문자기반 비교

|바이트기반 스트림| 문자기반 스트림| 
|:--:|:--:|
|FileInputStream , FileOutputStream|FileReader, FileWriter|
|ByteArrayInputStream, ByteArrayOutputStream| CharArrayReader, CharArrayWriter|
|PipedInputStream, PipedOutputStream|PipedReader , PipedWriter|
|StringBufferInputStream(deprecated), StringBufferOutputStream(deprecated)|StringReader, StringWriter| 


> 바이트기반과 문자기반 스트림의 메서드 비교

InputStream
       
    abstract int read()
    int read(byte[] b)
    int read(byte[] b, int off, int len)
Reader

    int read()
    int read(char[] cbuf)
    abstract int read(char[] cbuf, int off, int len)

OutputStream
    
    abstract void write(int b)
    void write(byte[] b)
    void write(byte[] b,int off, int len)

Writer

    void write(int c)
    void write(char[] cbuf)
    abstract void write(char[], int off, int len)
    void write(String str)
    void write(String str, int off, ing len)    

추상메서드가 아닌 메서드는 추상메서드로 작성
> 바이트기반, 문자기반 보조스트림

|바이트기반 보조스트림| 문자기반 스트림| 
|:--:|:--:|
|BufferedInputStream, BufferedOutputStream| BufferedReader, BufferedWriter|
|FilterInputStream, FilterOutputStream|FilterReader, FilterWriter|
|LineNumberInputStream(deprecated)|LineNumberReader|
|PrintStream|PrintWriter
|PushbackInputStream|PushbackReader|

## 2. 바이트기반 스트림
### 2.1 InputStream과 OutputStream

> InputStream의 메서드

|메서드명|설명| 
|:--:|:--:|
|int available()|스트림으로부터 읽어 올 수 있는 데이터의 크기 반환
|void close()|스트림을 닫음으로써 사용하고 있던 자원을 반환
|void mark(int readlimit)|현재 위치를 표시함, reset()에 의해 표시해 놓은 위치로 돌아갈 수 있다, readlimit은 되돌아갈수 있는 byte의 수
|boolean markSupported()|mark()와 reset()을 지원하는지를 알려줌, reset(),mark()는 선택적이므로 사용하기전에 markSUpporte()를 호출해서 지원여부 확인
|abstract int read()|1byte를 읽어옴, 더이상 읽어올 데이터가 없으면 -1
|int read(byte[] b)|배열 b의 크기만큼 읽고 배열을 채워서 읽은 데이터의 수 반환, 반환값은 항상 배열의 크기보다 작거나 같음
|int read(byte[] b, int offm ing len)|최대 len개의 byte를 읽어서, 배열 b의 지정된 위치(off)부터 저장
|void reset()|스트림에서의 위치를 마지막으로 mark()이 호출되었던 위치로 돌아간다
|long skip(long n)|스트림에서 주어진 길이만큼 건너뜀

> OutputStream의 메서드

|메서드명|설명|
|:--:|:--:|
|void close()|입력소스를 닫음으로써 사용하고 있던 자원을 반환한다
|void flush()|스트림의 버퍼에 있는 모든 내용을 출력소스에 쓴다
|abstract void write(int b)|주어진 값을 출력소스에 쓴다
|void write(byte[] b)|주어진 배열 b에 저장된 모든 내용을 출력소스에 쓴다
|void write(byte[] b, int off, int len)|주어진 배열 b에 저장된 내용 중 off 번째 부터 len개만큰 읽어서 출력소스에 쓴다

### 2.2 ByteArrayInputStream과 ByteArrayOutputStream

> 다른곳에 입출력하기 전에 데이터를 임시로 바이트배열에 담아서 작업

355p,예제 15-1,2,3,4

































