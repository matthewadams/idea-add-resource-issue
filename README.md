# idea-add-resource-issue

This repo illustrates an issue with IntelliJ IDEA's handling of the following `build-helper-maven-plugin` declarations at the following locations:

* https://github.com/matthewadams/idea-add-resource-issue/blob/master/Bar/pom.xml#L41
* https://github.com/matthewadams/idea-add-resource-issue/blob/master/Foo/pom.xml#L41

It appears as though the IDE is not honoring the plugin's request to `add-resource`.

Steps to reproduce:
1. Clone this repository
1. Although the repo is a multimodule maven project, _do not_ build the project via maven.
1. Open the project in IDEA by opening the `pom.xml` _in the root directory of the repo_ using IDEA.  (I used the command line `idea pom.mxl`.)
1. Open the Java test file `FooTest.java`.
1. Cause the IDE to run or debug test `testLoadResource`.
1. Observe test failure similar to the sample below.

## Workaround
The only workaround I can find is to build the project at the command line via maven (`mvn test` or similar).
Maven correctly honors the `add-resource` execution and the test passes.

## Additional information
If you
* edit the resource file (`foo.json` or `bar.json`) in the editor,
* update the test code to expect the new value, then
* rerun the test via the IDE,

you will observe the same error.
Again, building via maven _outside_ the IDE will allow subsequent runs of the test from within the IDE to pass.

## Sample Error

```
/Users/matthew/.sdkman/candidates/java/8.0.171-oracle/bin/java -ea -Didea.test.cyclic.buffer.size=1048576 "-javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=50622:/Applications/IntelliJ IDEA.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath "/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar:/Applications/IntelliJ IDEA.app/Contents/plugins/junit/lib/junit-rt.jar:/Applications/IntelliJ IDEA.app/Contents/plugins/junit/lib/junit5-rt.jar:/Users/ADA6011/.m2/repository/org/junit/platform/junit-platform-launcher/1.1.0/junit-platform-launcher-1.1.0.jar:/Users/ADA6011/.m2/repository/org/apiguardian/apiguardian-api/1.0.0/apiguardian-api-1.0.0.jar:/Users/ADA6011/.m2/repository/org/junit/platform/junit-platform-engine/1.1.0/junit-platform-engine-1.1.0.jar:/Users/ADA6011/.m2/repository/org/junit/platform/junit-platform-commons/1.1.0/junit-platform-commons-1.1.0.jar:/Users/ADA6011/.m2/repository/org/opentest4j/opentest4j/1.0.0/opentest4j-1.0.0.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/charsets.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/deploy.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/ext/cldrdata.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/ext/dnsns.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/ext/jaccess.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/ext/jfxrt.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/ext/localedata.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/ext/nashorn.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/ext/sunec.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/ext/sunjce_provider.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/ext/sunpkcs11.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/ext/zipfs.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/javaws.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/jce.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/jfr.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/jfxswt.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/jsse.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/management-agent.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/plugin.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/resources.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/jre/lib/rt.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/lib/ant-javafx.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/lib/dt.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/lib/javafx-mx.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/lib/jconsole.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/lib/packager.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/lib/sa-jdi.jar:/Users/ADA6011/.sdkman/candidates/java/8.0.171-oracle/lib/tools.jar:/private/tmp/multimodule-mvn-with-refs-outside-of-project/Bar/target/test-classes:/private/tmp/multimodule-mvn-with-refs-outside-of-project/Bar/target/classes:/Users/ADA6011/.m2/repository/commons-io/commons-io/2.6/commons-io-2.6.jar:/Users/ADA6011/.m2/repository/org/junit/jupiter/junit-jupiter-engine/5.1.0/junit-jupiter-engine-5.1.0.jar:/Users/ADA6011/.m2/repository/org/junit/jupiter/junit-jupiter-api/5.1.0/junit-jupiter-api-5.1.0.jar:/Users/ADA6011/.m2/repository/org/junit/jupiter/junit-jupiter-params/5.1.0/junit-jupiter-params-5.1.0.jar" com.intellij.rt.execution.junit.JUnitStarter -ideVersion5 -junit5 blackbox.BarTest,testLoadResource

java.io.IOException: Resource not found: /bar.json

	at org.apache.commons.io.IOUtils.resourceToURL(IOUtils.java:1372)
	at org.apache.commons.io.IOUtils.resourceToString(IOUtils.java:1293)
	at org.apache.commons.io.IOUtils.resourceToString(IOUtils.java:1272)
	at blackbox.BarTest.testLoadResource(BarTest.java:29)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:436)
	at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:115)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$6(TestMethodTestDescriptor.java:170)
	at org.junit.jupiter.engine.execution.ThrowableCollector.execute(ThrowableCollector.java:40)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:166)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:113)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:58)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.lambda$executeRecursively$3(HierarchicalTestExecutor.java:112)
	at org.junit.platform.engine.support.hierarchical.SingleTestExecutor.executeSafely(SingleTestExecutor.java:66)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.executeRecursively(HierarchicalTestExecutor.java:108)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.execute(HierarchicalTestExecutor.java:79)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.lambda$executeRecursively$2(HierarchicalTestExecutor.java:120)
	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:175)
	at java.util.Iterator.forEachRemaining(Iterator.java:116)
	at java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1801)
	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.lambda$executeRecursively$3(HierarchicalTestExecutor.java:120)
	at org.junit.platform.engine.support.hierarchical.SingleTestExecutor.executeSafely(SingleTestExecutor.java:66)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.executeRecursively(HierarchicalTestExecutor.java:108)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.execute(HierarchicalTestExecutor.java:79)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.lambda$executeRecursively$2(HierarchicalTestExecutor.java:120)
	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:175)
	at java.util.Iterator.forEachRemaining(Iterator.java:116)
	at java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1801)
	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.lambda$executeRecursively$3(HierarchicalTestExecutor.java:120)
	at org.junit.platform.engine.support.hierarchical.SingleTestExecutor.executeSafely(SingleTestExecutor.java:66)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.executeRecursively(HierarchicalTestExecutor.java:108)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.execute(HierarchicalTestExecutor.java:79)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:55)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:43)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:170)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:154)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:90)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:74)
	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)


Process finished with exit code 255
```
