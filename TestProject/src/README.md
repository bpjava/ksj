# 날짜와 시간

## Calendar와 Date

1. Calendar

    - 추상클래스
    - Calendar을 상속받아 완전히 구현한 클래스:
        - GregorianCalendar(태국 외의 국가)
        - BuddhistCalendar(태국)
    - 사용방법
    > Calendar cal = new Calendar();    //에러! 

    추상클래스이기 때문에 인스턴스를 생성할 수 없음

    > Calendar cal = CAlendar.getInstance();    //getInstance
    
    ()메서드는 Calendar 클래스를 구현한 클래스의 인스턴스를 반환


2. Date와 Calendar

    - Calendar클래스가 추가되면서 Date는 잘 사용되지 않음.
    - Calendar->Date, Date->Calendar 로 변환할 일이 생기기도 함.

    > 1. Calendar->Date:<br>
    >    Calendar cal = Calendar.getInstance();<br>
    >    Date d = new Date(cal.getTimeInMillis()); 또는 Date d = cal.getTime();
    
    > 2. Date->Calendar:<br>
    >    Date d = new Date;<br>
    >    Calendar cal = Calendar.getInstance();
    >    cal.setTime(d);

    - get메서드: Calendar의 int상수를 가져와서 날짜와 시간에 대한 정보를 알려줌(예제 10-1(page13))
    - set메서드: 원하는 날짜나 시간으로 설정(예제 10-2(page14))
    - 시간 차이 구하기: (예제 10-3(page15))
    - add메서드: 지정한 필드의 값을 원하는 만큼 증가 또는 감소시켜줌(예제 10-3(page17))
    - roll메서드: add메서드와 유사하나. 다른 필드에 영향을 미치지 않는다.(예제 10-5(page16))

