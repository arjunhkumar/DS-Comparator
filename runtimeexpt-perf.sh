#!/bin/bash
echo "Starting Linescale"

perf stat -r 10 -e instructions -d  $SANITY_JDK/java -Xverify:none -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.0-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.NonValueMain 32000 > time-lsnvt.log 2>&1

echo "Finished Linescale-NVT"

perf stat -r 10 -e instructions -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.0-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.ValueMain 32000 > time-lsvt.log 2>&1

echo "Finished Linescale-VT"

perf stat -r 10 -e instructions -d $SANITY_JDK/java -Xverify:none  -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.0-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.ValueMain 32000 > time-lsvt.log 2>&1

echo "Finished Linescale-VT Without Threshold"

echo "Starting TA"

perf stat -r 10 -e instructions -d $SANITY_JDK/java -Xverify:none  -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.0-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.transaction.processing.suite.NonValueMain 32000 > time-tanvt.log 2>&1

echo "Finished Linescale-NVT"

perf stat -r 10 -e instructions -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.0-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.transaction.processing.suite.ValueMain 32000 > time-tavt.log 2>&1

echo "Finished Linescale-VT"

perf stat -r 10 -e instructions -d $SANITY_JDK/java -Xverify:none -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.0-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.transaction.processing.suite.ValueMain 32000 > time-tavt.log 2>&1

echo "Finished Linescale-VT Without Threshold"

