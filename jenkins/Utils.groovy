#!groovy
def storeCurrentCommitId(){
    def CURRENT_COMMIT = sh(
            script: 'git log --pretty=format:"%H" -n1',
            returnStdout: true
    ).trim()
    writeFile(file: "current_commit_${env.BUILD_NUMBER}", text: "${CURRENT_COMMIT}")
    archiveArtifacts(artifacts: "current_commit_${env.BUILD_NUMBER}")
    return CURRENT_COMMIT
}
def getPreviousBuildCommitId(){
    if(currentBuild.previousBuild){
        try {
            copyArtifacts(projectName: currentBuild.projectName,
                    selector: specific("${currentBuild.previousBuild.number}"))
            def exists = fileExists("current_commit_${currentBuild.previousBuild.number}")
            if (exists) {
                def prev_commit = readFile("current_commit_${currentBuild.previousBuild.number}").trim()
                return prev_commit
            } else {
                return null
            }
        }catch(e) {
            echo "${e}"
            return null
        }
    }
}
return this;