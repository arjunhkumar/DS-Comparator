#!/bin/bash
echo "Starting Linescale - Normal"

time $SANITY_JDK/java -Xverify:none -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* NonValueMain 10000 > time-n-lsnvt.log 2>&1

echo "Finished Linescale-NVT"

time $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* ValueMain 10000 > time-n-lsvt.log 2>&1

echo "Finished Linescale-VT"

time $SANITY_JDK/java -Xverify:none  -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* ValueMain 10000 > time-n-lsvt.log 2>&1

echo "Finished Linescale-VT Without Threshold"

echo "Starting Linescale - Compiled only"

time $SANITY_JDK/java -Xverify:none -Xjit:count=0 -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* NonValueMain 10000 > time-c-lsnvt.log 2>&1

echo "Finished Linescale-NVT"

time $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=0 -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* ValueMain 10000 > time-c-lsvt.log 2>&1

echo "Finished Linescale-VT"

time $SANITY_JDK/java -Xverify:none  -Xjit:count=0 -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* ValueMain 10000 > time-c-lsvt.log 2>&1

echo "Finished Linescale-VT Without Threshold"


echo "Starting Linescale - Interpreted only"

time $SANITY_JDK/java -Xverify:none -Xint -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* NonValueMain 10000 > time-i-lsnvt.log 2>&1

echo "Finished Linescale-NVT"

time $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xint -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* ValueMain 10000 > time-i-lsvt.log 2>&1

echo "Finished Linescale-VT"

time $SANITY_JDK/java -Xverify:none  -Xint -cp ds-comperator-1.1-SNAPSHOT.jar:lib/* ValueMain 10000 > time-i-lsvt.log 2>&1

echo "Finished Linescale-VT Without Threshold"
