#!/bin/bash
echo "Starting test"

perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.NonValueMain 100 > nonValueFieldContainerTest10.log 2>&1

echo "Finished NonValueFieldContainerTest 100"


perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.IntermediateValueMain 100 > iValueFieldContainerTest10.log 2>&1

echo "Finished IValueFieldContainerTest 100"


perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.ValueMainLarge 100 > ValueFieldContainerTestLarge10.log 2>&1

echo "Finished ValueFieldContainerTestLarge 100"

perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.ValueMainMeduim 100 > valueFieldContainerTestMeduim10.log 2>&1

echo "Finished ValueFieldContainerTestMeduim 100"

perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.ValueMainSmall 100 > valueFieldContainerTestSmall10.log 2>&1

echo "Finished ValueFieldContainerTestSmall 100"

perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.ValueMainXLarge 100 > valueFieldContainerTestXL10.log 2>&1

echo "Finished ValueFieldContainerTestXL 100"

perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.ValueMain 100 > valueFieldContainerTest10.log 2>&1

echo "Finished ValueFieldContainerTest 100"

perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.NonValueMain 200 > nonValueFieldContainerTest50.log 2>&1

echo "Finished NonValueFieldContainerTest 200"


perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.IntermediateValueMain 200 > iValueFieldContainerTest50.log 2>&1

echo "Finished IValueFieldContainerTest 200"


perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.ValueMainLarge 200 > ValueFieldContainerTestLarge50.log 2>&1

echo "Finished ValueFieldContainerTestLarge 200"

perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.ValueMainMeduim 200 > valueFieldContainerTestMeduim50.log 2>&1

echo "Finished ValueFieldContainerTestMeduim 200"

perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.ValueMainSmall 200 > valueFieldContainerTestSmall50.log 2>&1

echo "Finished ValueFieldContainerTestSmall 200"

perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.ValueMain 200 > valueFieldContainerTest50.log 2>&1

echo "Finished ValueFieldContainerTest 200"

perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.ValueMainXLarge 100 > valueFieldContainerTestXL10.log 2>&1

echo "Finished ValueFieldContainerTestXL 200"

perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.NonValueMain 500 > nonValueFieldContainerTest100.log 2>&1

echo "Finished NonValueFieldContainerTest 500"


perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.IntermediateValueMain 500 > iValueFieldContainerTest100.log 2>&1

echo "Finished IValueFieldContainerTest 500"


perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.ValueMainLarge 500 > ValueFieldContainerTestLarge100.log 2>&1

echo "Finished ValueFieldContainerTestLarge 500"

perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.ValueMainMeduim 500 > valueFieldContainerTestMeduim100.log 2>&1

echo "Finished ValueFieldContainerTestMeduim 500"

perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.ValueMainSmall 500 > valueFieldContainerTestSmall100.log 2>&1

echo "Finished ValueFieldContainerTestSmall 500"

perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.ValueMain 500 > valueFieldContainerTest100.log 2>&1

echo "Finished ValueFieldContainerTest 500"

perf stat -r 5 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xms4G -Xmx12G -Xverify:none -XX:ValueTypeFlatteningThreshold=9999999999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-4.2-small-instance.jar:lib/* in.ac.iitmandi.compl.ValueMainXLarge 500 > valueFieldContainerTestXL10.log 2>&1

echo "Finished ValueFieldContainerTestXL 500"

