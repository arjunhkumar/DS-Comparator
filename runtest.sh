#!/bin/bash
echo "Starting Linescale"

perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none  -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.0-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.NonValueMain 10000 > lsnvt.log 2>&1

echo "Finished Linescale-NVT 100"

perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.0-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.ValueMain 10000 > lsvt.log 2>&1

echo "Finished Linescale-VT 100"

echo "Starting TA"

perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none  -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.0-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.transaction.processing.suite.NonValueMain 10000 > tanvt.log 2>&1

echo "Finished Linescale-NVT 100"

perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.0-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.transaction.processing.suite.ValueMain 10000 > tavt.log 2>&1

echo "Finished Linescale-VT 100"

