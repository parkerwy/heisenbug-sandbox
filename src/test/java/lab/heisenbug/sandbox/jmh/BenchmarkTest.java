package lab.heisenbug.sandbox.jmh;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class BenchmarkTest {

    @Test
    public void launchBenchmark() throws Exception {

        Options opt = new OptionsBuilder()
                // Specify which benchmarks to run.
                // You can be more specific if you'd like to run only one benchmark per test.
                .include(this.getClass().getName() + ".*")
                // Set the following options as needed
                .mode(Mode.AverageTime).timeUnit(TimeUnit.MICROSECONDS).warmupTime(TimeValue.seconds(1))
                .warmupIterations(2).measurementTime(TimeValue.seconds(1)).measurementIterations(2).threads(2).forks(1)
                .shouldFailOnError(true).shouldDoGC(true)
                // .jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                // .addProfiler(WinPerfAsmProfiler.class)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void benchmark1(Blackhole blackhole) {
        int a = 1;
        int b = 2;
        int sum = a + b;
        blackhole.consume(sum);
    }
}
