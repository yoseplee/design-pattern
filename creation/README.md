# creation pattern

## 1. Singleton Pattern
> Program 전체에서 유일한 인스턴스 하나만 만들어서 쓰자! 객체가 1개만 생성하는 것을 보장하는 것이 핵심

#### Step1. getInstance
```java
public class What {
    private What() {}
    public static What getInstance() {
        return new What();
    }
}
```
단연컨대 가장 간단하고, 가장 상황을 고려하지 않아 위험한 코드이다. 객체를 생성해서 넘겨준다는 임무만 충실히 수행하고 있다. 이 상황에서는 객체가 한개 이상 생성되는 문제가 발생할 수 있으며 발생하곤 하며 애를 먹이기도 하고 짜증이 나며 머리숱이 빠지고(...) 동료와 다투는 문제(코드를 발로 짜니? 대뇌 피질 주름의 깊이가 야구공이니(양각이다) 정도의 정겨운 소리가 난무하곤 한다)로 비화되곤 한다.

#### Step2. Flag
```java
public class What {
    private static What onlyWhat;
    private What() {}

    public static What getInstance() {
        if(onlyWhat == null) {
            onlyWhat = new What();
        }
        return onlyWhat;
    }
}
```
플래그를 써서 객체가 만들어진 경우 이를 표시하고 매번 확인하는 전략이다. 간단하고, 훌륭하다. 하지만 멀티스레딩 환경을 고려한다면 이 또한 아직 부족하다. critical section(concurrency를 반드시 고려해야 하는 부분)을 지정하고 thread lock을 걸어주는 것이 핵심이다.

#### Step3. Concurrency - coalse grained lock
```java
public class What {
    private static What onlyWhat;
    private What() {}

    public synchronized static What getInstance() {
        if(onlyWhat == null) {
            onlyWhat = new What();
        }
        return onlyWhat;
    }
}
```
locking에는 사실 크게 두 가지 접근 방법이 있다.

* Coalse-grained Lock
* Fine-grained Lock

이건 뭐 핸드밀에 곡물을 갈아 넣는것을 비유하는것도 아니고. 전자는 넉넉하게 갈아버린(?), 후자는 꼼꼼히 갈아버린 뜻이 되겠다. 주변 사물에서 찾아보자면 손절구로 찧은 것은 coalse-grained, 맺돌로 갈아버린 것은 fine-grained가 되겠다.

잡소리가 길었지만 이해에 도움이 되길 바란다 미래의 나여(?). Lock으로 생각했을 때는 널찍하게 함수 앞뒤로 락을 걸어버리는 것이 전자, 함수 내부에서 Concurrency 이슈가 발생할 수 있는 critical section을 식별하고 그 지점에만 락을 걸어버리는 것이 후자가 되겠다.

당연히 coalse-grained는 fine-grained에 비해 비용적으로 불리하다. 그러나 개발에 있어서 fine-grained는 매우 어려운 편이다. 과거 학부 시절 이진 트리의 삽입, 삭제에 fine-grained를 구현하는데 매우 어려웠던 기억이 있다. 당시엔 디버거를 쓸 생각도 못해서... 휴...

#### Step4. Concurrency - Double locking
```java
public class What {
    private static What onlyWhat;
    private What() {}

    public static What getInstance() {
        if(onlyWhat == null) {
            synchronized(What.class) {
                if(onlyWhat == null) {
                    onlyWhat == new What();
                }
            }
        }
        return onlyWhat;
    }
}
```
이 변태들(사랑해요!)! Step3. 정도면 만족할만도 한데 한걸음 더 나아갔다. IBM에서 제안되었다고 알려진(...교수님 피셜) double locking 방법이다. 생각해보자. getInstance을 실행할 때 첫 비교문에서 null에 걸릴 likelihood가 얼마나 있나? 가만보자... 프로그램이 실행되는 초기 이후에는 거의 없겠구나! 그리고 재수없게 비교문 안에서 context switching이 발생할 likelihood는...? 거의 없겠구나! 정말 재수가 없다면 말이지 하하하. 그러면 일반적으로 확률이 높은 처리를 하게 두고, 그 재수없는 경우에 대해 예방 차원에서 넣어놓자~ 라는게 아이디어다. 으휴 이 변태들(팬이예요!).