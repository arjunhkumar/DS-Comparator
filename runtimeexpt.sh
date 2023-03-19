#!/bin/bash
echo "Starting Linescale"

time $SANITY_JDK/java -Xverify:none -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.0-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.NonValueMain 10000 > time-lsnvt.log 2>&1

echo "Finished Linescale-NVT"

time $SANITY_JDK/java -Xverify:none -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.0-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.linescale.suite.ValueMain 10000 > time-lsvt.log 2>&1

echo "Finished Linescale-VT"

echo "Starting TA"

time $SANITY_JDK/java -Xverify:none -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.0-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.transaction.processing.suite.NonValueMain 10000 > time-tanvt.log 2>&1

echo "Finished Linescale-NVT"

time $SANITY_JDK/java -Xverify:none -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.0-SNAPSHOT.jar:lib/* in.ac.iitmandi.compl.transaction.processing.suite.ValueMain 10000 > time-tavt.log 2>&1

echo "Finished Linescale-VT"

