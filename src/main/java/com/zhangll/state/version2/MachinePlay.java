package com.zhangll.state.version2;

import java.io.IOException;
import java.io.Serializable;

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

abstract class State implements Action, Serializable {
    private static final long serialVersionUID = -123123L;
    protected final String name;
    protected final int score;
    protected transient final MachinePlay machinePlay;

//    protected State(String name, int score) {
//        this(name, score, new MachinePlay());
//    }

    public State(String name, int score, MachinePlay machinePlay) {
        this.name = name;
        this.score = score;
        this.machinePlay = machinePlay;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}


class SmallMaria extends State {


    protected SmallMaria(String name, int score, MachinePlay machinePlay) {
        super(name, score,machinePlay);
    }

    @Override
    public void E1() {
//        state = new SuperMaria("super",state.score + 100);
        this.machinePlay.setState(new SuperMaria("super",score + 100,this.machinePlay));
    }

    @Override
    public void E2() {
        this.machinePlay.setState(new CapeMaria("cap", this.score + 200 ,this.machinePlay));
    }

    @Override
    public void E3() {
        this.machinePlay.setState(new FireMaria("fire", this.score + 300 ,this.machinePlay));
    }

    @Override
    public void E4() {

    }
}

class FireMaria extends State {

    protected FireMaria(String name, int score, MachinePlay machinePlay) {
        super(name, score, machinePlay);
    }


    @Override
    public void E1() {

    }

    @Override
    public void E2() {

    }

    @Override
    public void E3() {

    }

    @Override
    public void E4() {
        this.machinePlay.setState(new SmallMaria("small", score - 300, this.machinePlay));
    }
}

class CapeMaria extends State {


    protected CapeMaria(String name, int score, MachinePlay machinePlay) {
        super(name, score, machinePlay);
    }


    @Override
    public void E1() {

    }

    @Override
    public void E2() {

    }

    @Override
    public void E3() {

    }

    @Override
    public void E4() {
        this.machinePlay.setState(new SmallMaria("small", score - 200, this.machinePlay));
    }
}

class SuperMaria extends State {


    protected SuperMaria(String name, int score, MachinePlay machinePlay) {
        super(name, score,machinePlay);
    }

    @Override
    public void E1() {

    }

    @Override
    public void E2() {
        this.machinePlay.setState(new CapeMaria("cap", score + 200, this.machinePlay));
    }

    @Override
    public void E3() {

        this.machinePlay.setState(new FireMaria("fire", score + 300, this.machinePlay));

    }

    @Override
    public void E4() {
        this.machinePlay.setState(new SmallMaria("small", score - 100, this.machinePlay));
    }
}


public class MachinePlay implements Action {
    public State state;


    public void setState(State state) {
        this.state = state;
    }

    public void initState(State state){
        // 反序列化的文件
//        System.out.println(state);
//        System.out.println(state.name);
//        System.out.println(state instanceof SuperMaria);
//        System.out.println(state instanceof SmallMaria);
//
//        switch (state.name){
//            case "small":
//                this.state = new SmallMaria(state.name, state.score, this);
//            case "super":
//                this.state = new SuperMaria(state.name, state.score, this);
//
//        }
        if(state instanceof SmallMaria){
            this.state = new SmallMaria(state.name, state.score, this);
        }
        if(state instanceof SuperMaria){
            this.state = new SuperMaria(state.name, state.score, this);
        }
        if(state instanceof CapeMaria){
            this.state = new CapeMaria(state.name, state.score, this);
        }

        if(state instanceof FireMaria){
            this.state = new FireMaria(state.name, state.score, this);
        }



    }


    public MachinePlay() {
        this.state = new SmallMaria("small", 0, this);
    }


    @Override
    public void E1() {
        state.E1();
    }

    @Override
    public void E2() {
       state.E2();
    }

    @Override
    public void E3() {
        state.E3();
    }

    @Override
    public void E4() {
        state.E4();
    }

    @Override
    public String toString() {
        return "MachinePlay{" +
                "state=" + state +
                '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // 序列化对象
//        MachinePlay machinePlay = new MachinePlay();
//        machinePlay.E1();
//        SeriUtil.serilize(machinePlay.state);
        // 反序列化对象
        MachinePlay machinePlay = new MachinePlay();
        State state = (State) SeriUtil.deSerilize("com.zhangll.state.version2.SuperMaria");

        machinePlay.initState(state);

        machinePlay.E2();
//        machinePlay.E4();
//        machinePlay.E3();
//        machinePlay.E3();
//        machinePlay.E3();
//        machinePlay.E3();
//        machinePlay.E3();
//        machinePlay.E4();

        System.out.println(machinePlay);
    }
}
