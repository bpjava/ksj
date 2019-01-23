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

### 2.3 FileInputStream과 FileOutputStream

> 파일에 입출력을 하기 위한 스트림. 실제 많이 사용되는 스트림

> FileInputStream, FileOutputStream 생성자

|생성자|설명|
|:--:|:--:|
|FileInputStream(String name)|지정된 파일이름을 가진 실제 파일과 연결된FileInputStream 생성
|FileInputStream(File file)|파일의 이름이 String이 아닌 File인스턴스로 지정,FileInputStream(String name)과 같다|
|FileInputStream(FileDescripter fdObj)|파일 디스크립터(fdObj)로 FileInputStream생성|
|FileOutputStream(String name)|지정된 파일이름을 가진 실제 파일과의 연결된 FileOutStream을 생성
|FileOutputStream(String name,boolean append)|지정된 파일이름을 가진 실제 파일과 연결된 FileOutputStream을 생성, 두번째 인자인 append를 true로 하면, 출력시 기존의 파일내용을 마지막에 덧붙임,false면 기존의 파일내용을 덮어씀
|FileOutputStream(File file)|파일이름을 File인스턴스로 지정,FileOtputStream(String name)과 같음
|FileOutputStream(File file,boolean append)|파일이름 File인스턴스
|FileOutputStream(FileDescriptor fdObj)|파일 디스크립터로 FileOutputStream을 생성

360p,예제15-6

## 3. 바이트기반의 보조스트림
### 3.1 FilterInputStream과 FilterOutputStream

> InputStream/OutputStream의 자손, 모든 보조스트림의 조상

보조스트림은 자체적으로 입출력 수행 불가능해서 기반 스트림을 필요로 함

       ptotected FilterInputStream(InputStream in)
       public FilterOutputStream(OutputStream out)


- 자체적으로 아무 일도 하지않고 단순히 기반스트림의 메서드를 그대로 호출함
- 상속을 통해 원하는 작업을 수행하도록 읽고 쓰는 메서드를 오버라이딩해야 한다

       public class FilterInputStream extends InputStream{
           protected volatile InputStream in;
           protected FilterInputStream(InputStream in){
               this.in=in;
           }
           public int read() throws IOException{
               return in.read();
           }
           ...
       }

- 제어접근자가 protected 이기 때문에 FilterInputStream의 인스턴스르 생성해서 사용할 수 없고 상속을 통해서 오버라이딩되어야 한다
  - FilterInputStream의 자손 - BufferedInputStream, DataInputStream, PushbackInputStream 등
  - FilterOutputStream의 자손 - BufferedOutputStream, DataOutputStream, PtintStream 등

### 3.2 BufferedInputStream과 BufferedOutputStream

> 입출력 효율을 높이기 위해 버퍼를 사용하는 보조스트림
- 한 바이트씩 입출력하는 것보다 버퍼를 이용해서 한번에 여러 바이트 입출력하는 것이 빠르게 때문에 사용

 > BufferedInputStream의 생성자

 |생성자|설명|
 |:--:|:--:|
 |BufferedInputStream(InpuStream in, int size)|주어진 IputStream인스턴스를 입력소스로 하며 지정된 크기의 버퍼를 갖는 BufferedInputStream인스턴스 생성
 |BufferedInputStream(InputStream in)|주어진 InputSteam인스턴스를 입력소스로 하며 버퍼의 크기를 지정하지 않았으므로 기본적으로 8192 byte크기 버퍼 가짐

> 버퍼 크기지정
- BufferedInputStream 의 버퍼크기는 입력소스로부터 한 번에 가져올 수 있는 데이터 크기로 지정
- 입력소스가 파일인 경우 보통 1024~2048또는 4096 크기

> 특징
- read를 호출하면, 입력소스로부터 버퍼 크기만큼 데이터를 읽어다 자신의 내부버퍼에 저장
- 프로그램에선 버퍼에 저장된 데이터를 읽으면 됨
- 외부의 입력소스로 부터 읽는 것보다 내부 버퍼에서 읽는 것이 더 빠름

> BufferedOutputStream 생성자와 메서드

|메서드/생성자|설명|
|:--:|:--:|
|BufferedOutputStream(OutputStream out, int size)|주어진 OutputSTream인스턴스를 출력소스로 하며 지정된 크기를 버퍼를 가짐
|BufferedOutputStream(OutputStream out)|주어진 OutputStream인스턴스를 출력소스로하며 버퍼의 크기를 지정해주지 않으므로 기본적으로 8192byte 크기를 갖는다
|flush()|버퍼의 모든 내용을 출력소스에 출력한 후 버퍼를 비운다
|close()|flush()를 호출해서 버퍼의 모든 내용을 출력소스에 출력,BufferedOutputStream인스턴스가 사용하던 모든 자원을 반환

- 프로그램에서 write메서드를 이용한 출력이 BufferedOutputStream의 버퍼에 저장됨
- 버퍼가 가득 차면, 그 때 버퍼의 모든 내용을 출력소스에 출력함
- 버퍼를 비우고 다시 출력을 저장할 준비함
    - 마지막 출력부분이 출력소스에 쓰이지 못하고 버퍼에 남아있는 채로 프로그램이 종료될 수 있다는 걸 주의

364p,예제15-7

> FilterOutputStream

         public class FilterOutputStream extends OutputStream{
             protected OutputStream out;
             public FilterOutputStream(OutputStream out){
                 this.out = out;
             }
             ...
         
         public void close() throws IOException{
             try(
                 flush();
            }catch(IOException ignored{}
            out.close(); //기반스트림의 close() 호출
         }
     }
   
365p

 ### 3.3 DataInputStream과 DataOutputStream

   > FilterInputStream/FilterOutput의 자손

   - byte단위가 아닌, 8가지 기본 자료형의 단위로 읽고 쓸 수 있다
   - 각 기본 자료형 값을 16진수로 표현하여 저장

 366p, 표 참고

367p, 예제15-9,15-10,15-11,15-12

### 3.4 SequenceInputStream

> 여러개의 입력스트림을 연속적으로 연결해서 하나의 스트림으로부터 데이터를 읽는 것과 같이 처리할 수 있도록 함

- 큰 파일을 여러 개의 작은 파일로 나눴다가 하나의 파일로 합치는 것과 같은 작업을 수행할 때 사용하면 좋음

> SequenceInputStream 생성자

|메서드/생성자|설명
|:--:|:--:|
|SequenceInputStream(Enumeration e)|Enumeration에 저장된 순서대로 입력스트림을 하나의 스트림으로 연결
|SequenceInputStream(InputStream s1,InputStream s2)|두 개의 입력스트림을 하나로 연결

> Vector에 연결할 입력스트림들을 저장한 후 Vector의 Enumeration elements()를 호출해서 생성자의 매개변수로 사용

373p,예제 15-14
- Vector에 저장된 순서대로 입력되므로 순서에 주의

### 3.5 PrintStream

> 데이터를 기반스트림에 다양한 형태로 출력할 수 있는 print, println, printf와 같은 메서드를 오버로딩하여 제공

- 데이터를 문자로 출력하므로 문자기반스트림
- PrintStream과 PrintWriter은 거의 같은 기능이지만 PrintWriter가 다양한 언어의 문자를 처리하는데 적합


374p PrintStream의 생성자, 메서드


## 4. 문자기반 스트림

### 4.1 Reader와  Writer

> byte배열 대신 char배열을 사용한다는 것 외엔 InputStream/OutputStream 과 비슷하다

- Reader는 특정 인코딩을 읽어서 유니코드로 변환, Writer는 유니코드를 특정 인코딩으로 변환하여 저장

p378 Reader,Writer메서드 종류 표

### 4.2 FileReader와 FileWriter
> 파일로부터 텍스트데이터를 읽고, 파일에 쓰는데 사용

FileInputStream/FileOutputStream 과 사용방법 같음

p379 예제15-16

### 4.3 PipedReader와 PipedWriter

> 쓰레드 간에 데이터를 주고받을 때 사용

- 다른 스트림과 다르게 입력과 출력을 하나의 스트림으로 연결해서 데이터를 주고받음

- 스트림 생성 후, 한쪽 쓰레드에서 connect()를 호출해서 입,출력 스트림을 연결
- 입출력 마친 후, 어느 한쪽 스트림만 닫아도 나머지 스트림 자동으로 닫힘

p381,예제 15-18

### 4.4 StringReader와 StringWriter

> CharArrayReader/CharArrayWriter와 같이 입출력 대상이 메모리인 스트림

- StringWriter에 출력되는 데이터는 내부의 StringBuffer에 저장됨

       StringBuffer getBuffer()  StringWriter에 출력한 데이터가 저장된 StringBuffer를 반환
       String toString()  StringWriter에 출력된(버퍼에 저장된) 문자열을 반환

p383 예제15-19       

## 5. 문자기반 보조스트림

### 5.1 BufferedReader 와 BufferedWriter 

> 버퍼를 이용해서 입출력의 효율을 높일 수 있도록 해줌

- BufferedReader 의 readLine() : 데이터를 라인단위로 읽어 올 수 있음 
- BUfferedWriter 의 newLine() : 줄바꿈 해줌

p384, 예제15-20

### 5.2 InputStreamReader와 OutputStreamWriter

> 바이트기반스트림을 문자기반스트림으로 연결해줌

-  바이트기반 스트림의 데이터를 지정된 인코딩 문자데이터로 변환

> InputStreamReader의 생성자,메서드

|생성자/메서드|설명|
|:--:|:--:|
|InputStreamReader(InputStream in)|OS에서 사용하는 기본 인코딩의 문자로 변환|
|InputStreamReader(InputStream in,String encoding)|지정된 인코딩을 사용|
|String getEncoding()|InputSTreamReader의 인코딩을 알려줌|

> OutputSTreamWriter의 생성자,메서드

|생성자/메서드|설명|
|:--:|:--:|
|OutputStreamWriter(OuputStream in)|OS에서 사용하는 기본 인코딩의 문자로 변환|
|OutputStreamWriter(OuputStream in,String encoding)|지정된 인코딩을 사용|
|String getEncoding()|OutputStreamWriter의 인코딩을 알려줌|

- 한글윈도우에서 중국어로 작성된 파일을 읽을 때 InputStreamReader(InputSteam in, String encoding)를 이용해서 인코딩이 중국어로 되어 있다는 것을 지정해야함
- 파일에 텍스트 데이터를 저장할때 OutputStreamWriter(InputSteam in, String encoding)을 이용해서 인코딩을 지정


p386, 에제15-21


## 6. 표준입출력과 File
### 6.1 표준입출력 -System.in, System.out, System.err

> 표준입출력: 콘솔을 통한 데이터 입력과 콘솔로의 데이터 출력

> 자바 어플 실행과 동시에 자동적으로 생성됨. 별도 스트림 생성 필요 없음

- System.in : 콘솔로부터 데이터를 입력받음
- System.out : 콘솔로 데이터를 출력
- System.err : 콘솔로 데이터를 출력

p388

### 6.2 표준입출력의 대상변경 - setOut(), setErr(), setIn()

> 입출력을 콘솔 이외의 다른 입출력 대상으로 변경하는 것이 가능

|메서드|설명|
|:--:|:--:|
|static void setOut (PrintStream out)|System.out의 출력을 지정된PrintStream으로 변경|
|static void setErr (PrintStream err)|System.err의 출력을 지정한PrintStream으로 변경|
|static void setIn (InputStream in)|System.in의 출력을 지정된inputStream으로 변경|

p391, 예제15-24


### 6.3 RandomAccessFile

> 하나의 클래스로 파일에 대한 입력,출력을 모두 할 수 있다

- InputStream, OutputStream으로부터 상속받지 않음
-  DataInput, DataOutput인터페이스를 모두 구현한다
- DataInputStream, DataOutputStream처럼 기본자료형 단위로 데이터 읽고 씀
     - 파일의 어느 위치나 가능
     - 내부 파일 포인터 사용

> RandomAccexxFile 생성자,메서드 

|생성자/메서드|설명|
|:--:|:--:|
|RandomAccessFile(File file, String mode)RandomAccessFile(String fileNme, String mode)|주어진 file에 읽기와 쓰기를 하기 위한 RandomAccessFile인스턴스를 생성|
|FileChannel getChannel()|파일의 파일채널 반환|
|FileDescriptor getFD()|파일의 파일 디스크립터 반환|
|long getFilePointer()|파일의 포인터 위치 알려줌
|long length()|파일의 크기 얻음
|void seek(long pos)|파일 포인터의 위치를 변경
|void setLength(long newLength)|파일의 크기를 지정된 길이로 변경
|int skipBytes(int n)|지정된 수만큼의 byte를 건너뜀

p394,예제15-25,26


### 6.4 File

> File의 생성자와 경로 메서드

|생성자/메서드|설명|
|:--:|:--:|
|File(String fileName)|fileName을 이름으로 갖는 파일을 위한 File인스턴스 생성,프로그램 실행되는 위치가 경로로 간주|
|File(String pathName, String fileName) File(File pathName, String fileName)|파일의 경로와 이름을 따로 분리해서 지정|
|File(URL url) |지저된 url로 파일생성|
| String getName()| 파일이름을 String으로 반환|
|String getPath()|파일의 경로를 String으로 반환
|String getAbsolutePath()|파일의 절대경로를 String으로 반환
| String getAbsoluteFile()| 파일의 절대경로를 File로 반환
|String getParent()|파일의 조상 디렉토리를 String으로 반환
|File getParentFile()|파일의 조상 디렉토리를 File로 반환
|String getcanonicalPath()|파일의 정규경로를 String로 반환|
|File getcanonicalFile()|파일의 정규경로를 File로 반환

> 경로와 관련된 File의 멤버변수

|멤버변수|설명|
|:--:|:--:|
|static String pathSeparator|OS에서 사용하는 경로 구분자, 윈도우";",유닉스":"
|static char pathSeparatorChar|OS에서 사용하는 경로 구분자, 윈도우";",유닉스":"
|static String separator|OS에서 사용하는 이름 구분자, 윈도우"W",유닉스"/"|
|static char separatorChar|OS에서 사용하는 이름 구분자, 윈도우"W",유닉스"/"

- 절대경로 : 파일시스템의 루트로부터 시작하는 파일의 전체 경로, 여러개 존재 가능
- 기호나 링크 등을 포함하지 않는 유일한 경로

p398.......
예제15-30,33,35,38
































## 7. 직렬화
### 7.1 직렬화란?

> 객체에 저장된 데이터를 스트림에 쓰기위해 연속적인 데이터로 변환하는 것

p415 그림15-7

- 객체는 오직 인스턴스변수들로만 구성됨
- 객체엔 메서드가 포함되지 않음
- 인스턴스변수는 인스턴스마다 다른 값을 가져야되기 때문에 별도의 메모리공간 필요

그림 15-8

### 7.2 ObjectInputStream，ObjectOutputStream

> 각각 InputStream, OutputStream을 직접 상속받고, 기반스트림을 필요하는 보조스트림


- 직렬화 : ObjectOutputStream
- 역직렬화 : ObjectInputStream

객체를 생성할 때 입출력할 스트림 지정

           ObjectInputStream(InputStream in)
           ObjectOutputStream(OutputSTream out)  

1. 파일에 객체를 저장하고 싶다면(직렬화)
         
         FileOutputStream fos = new FileOutputStream ("objectfile.ser");
         ObjectOutputStream out = new ObjectOutputStream (fos);

         out.writeObject(new UserInfo());

- 출력할 스트림 (FileOutputStream)을 생성
- 이를 기반스트림으로 하는 ObjectOutputStream 생성

2. 객체를 출력(역직렬화)

         
         FileInputStream fIs = new FileInputStream ("objectfile.ser");
         ObjectInputStream in = new ObjectInputStream (fis);

         UserInfo info = (UserInfo)in.readObject();

p417 메서드 표참고



### 7.3 직렬화가 가능한 클래스 만들기 - Serializable, transient

p418설명,419.....

예제15-39,40,41



### 7.4 직렬화가능한 클래스의 버전관리

> 직렬화된 객체를 역직렬화할 때는 직렬화 했을 때와 같은 클래스 사용

> 클래스 이름이 동일해도 내용이 변경된 경우 역직렬화 실패

- Object객체는 최고조상이므로 직렬화 불가
- String은 직렬화 가능
- transient 직렬화대상에서 제외


















