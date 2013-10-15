Benchmarks

Users: 10000
Ramp Up time: 1 sec
Loop: 2

/reactiveprogramming/service/sendecho
        Throughput: 57,980 req / sec
                    59,182 req / sec
                    56,509 req / sec

10 actors
/reactiveprogramming/sendecho
    Method within Actor
        Throughput: 53,752.755 req / sec
                    53,631.556 req / sec
                    52,607.062 req / sec

100 actors
/reactiveprogramming/sendecho
    Method within Actor
        Throughput: 53,558.853 req / sec
                    55,472.807 req / sec
                    54,461.451 req / sec
    Static Method in Utilities
        Throughput: 47,352.415 req / sec
                    54,009.164 req / sec
                    50,558.893 req / sec

1000 actors
/reactiveprogramming/sendecho
    Method within Actor
        Throughput: 53,558.853 req / sec
                    51,806.398 req / sec
                    54,275.068 req / sec

