package com.zhangll.state.hadoop;

public enum JobStateInternal {
    NEW,
    SETUP,
    INITED,
    RUNNING,
    SUCCESSED,
    KILLED
}
