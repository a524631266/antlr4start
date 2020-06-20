package com.zhangll.state.version1;

/**
 * 状态,事件,转移条件(action)
 */
interface Action{
    // e1 操作
    void E1();
    void E2();
    void E3();
    void E4();
}
abstract class State{
    protected final String name;
    protected final int score;

    protected State(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}


class SmallMaria extends State{


    protected SmallMaria(String name, int score) {
        super(name, score);
    }
}

class FireMaria extends State{

    protected FireMaria(String name, int score) {
        super(name, score);
    }

}
class CapeMaria extends State{


    protected CapeMaria(String name, int score) {
        super(name, score);
    }

}

class SuperMaria extends State{


    protected SuperMaria(String name, int score) {
        super(name, score);
    }
}


public class MachinePlay implements Action{
    public State state;

    public MachinePlay() {
        this.state = new SmallMaria("small", 0);
    }


    @Override
    public void E1() {
        if(state instanceof SmallMaria){
            state = new SuperMaria("super",state.score + 100);
        }
    }

    @Override
    public void E2() {
        if(state instanceof SmallMaria){
            state = new CapeMaria("cap",state.score + 200);
        }else if(state instanceof SuperMaria){
            state = new CapeMaria("cap", state.score + 200);
        }
    }

    @Override
    public void E3() {
        if(state instanceof SmallMaria){
            state = new FireMaria("fire",state.score + 300);
        }else if(state instanceof SuperMaria){
            state = new FireMaria("fire", state.score + 300);
        }
    }

    @Override
    public void E4() {
        if(state instanceof CapeMaria){
            state = new SmallMaria("small",state.score - 200);
        }else if(state instanceof SuperMaria){
            state = new SmallMaria("small", state.score - 100);
        } else if (state instanceof FireMaria){
            state = new SmallMaria("small", state.score - 300);
        }
    }

    @Override
    public String toString() {
        return "MachinePlay{" +
                "state=" + state +
                '}';
    }

    public static void main(String[] args) {
        MachinePlay machinePlay = new MachinePlay();
        machinePlay.E1();
        machinePlay.E2();
        machinePlay.E4();
        machinePlay.E3();
        machinePlay.E4();

        System.out.println(machinePlay);
    }
}
