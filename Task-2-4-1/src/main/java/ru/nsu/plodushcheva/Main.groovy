package ru.nsu.plodushcheva

class Main {


    //компиляция и генерация документации Java-проекта
    static def compileAndGenerateDoc(projectDir) {
        def javac = "javac"
        def javadoc = "javadoc"
        def sourceDir = "$projectDir/src"
        def outputDir = "$projectDir/bin"
        def docDir = "$projectDir/docs"

        def cmd = "${javac} -d ${outputDir} ${sourceDir}/*.java"
        def result = cmd.execute()

        if (result.exitValue() != 0) {
            println "Compilation failed"
            return
        }

        cmd = "${javadoc} -d ${docDir} -sourcepath ${sourceDir} ${sourceDir}/*.java"
        result = cmd.execute()

        if (result.exitValue() != 0) {
            println "Documentation generation failed"
            return
        }

        println "Compilation and documentation generation completed successfully"
    }

}
