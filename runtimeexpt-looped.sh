#!/bin/bash
echo "Starting Linescale"

echo "Starting Linescale - Normal"

perf stat -r 10 -e instructions -d $SANITY_JDK/java -Xverify:none -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.NonValueMain 320000 > time-n-lsnvt.log 2>&1

echo "Finished Linescale-NVT"

perf stat -r 10 -e instructions -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.ValueMain 320000 > time-n-lsvt.log 2>&1

echo "Finished Linescale-VT"

perf stat -r 10 -e instructions -d $SANITY_JDK/java -Xverify:none  -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.ValueMain 320000 > time-n-lsvt-w.log 2>&1

echo "Finished Linescale-VT Without Threshold"

echo "Starting Linescale - Compiled only"

perf stat -r 10 -e instructions -d $SANITY_JDK/java -Xverify:none -Xjit:count=0 -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.NonValueMain 320000 > time-c-lsnvt.log 2>&1

echo "Finished Linescale-NVT"

perf stat -r 10 -e instructions -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=0 -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.ValueMain 320000 > time-c-lsvt.log 2>&1

echo "Finished Linescale-VT"

perf stat -r 10 -e instructions -d $SANITY_JDK/java -Xverify:none  -Xjit:count=0 -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.ValueMain 320000 > time-c-lsvt-w.log 2>&1

echo "Finished Linescale-VT Without Threshold"


echo "Starting Linescale - Interpreted only"

perf stat -r 10 -e instructions -d $SANITY_JDK/java -Xverify:none -Xint -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.NonValueMain 320000 > time-i-lsnvt.log 2>&1

echo "Finished Linescale-NVT"

perf stat -r 10 -e instructions -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xint -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.ValueMain 320000 > time-i-lsvt.log 2>&1

echo "Finished Linescale-VT"

perf stat -r 10 -e instructions -d $SANITY_JDK/java -Xverify:none  -Xint -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.ValueMain 320000 > time-i-lsvt-w.log 2>&1

echo "Finished Linescale-VT Without Threshold"
