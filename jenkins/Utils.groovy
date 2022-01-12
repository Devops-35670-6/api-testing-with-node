#!groovy
def storeCurrentCommitId(){
    def CURRENT_COMMIT = sh(
            script: 'git log --pretty=format:"%H" -n1',
            returnStdout: true
    ).trim()
    writeFile(file: 'current_commit', text: "${CURRENT_COMMIT}")
    archiveArtifacts(artifacts: "${env.WORKSPACE}/current_commit")
    return CURRENT_COMMIT
}
def getPreviousBuildCommitId(){
    if(currentBuild.previousBuild){
        try {
            copyArtifacts(projectName: currentBuild.projectName,
                    selector: specific("${currentBuild.previousBuild.number}"))
            def exists = fileExists("${env.WORKSPACE}/current_commit")
            if (exists) {
                def prev_commit = readFile("${env.WORKSPACE}/current_commit").trim()
                return prev_commit
            } else {
                return null
            }
        }finally{
            return null
        }
    }
}
return this;