#!groovy
def storeCurrentCommitId(){
    def CURRENT_COMMIT = sh(
            script: 'git log --pretty=format:"%H" -n1'
    ).trim()
    writeFile(file: 'current_commit', text: "${CURRENT_COMMIT}")
    archiveArtifacts(artifacts: 'current_commit')
    return CURRENT_COMMIT
}
def getPreviousBuildCommitId(){
    if(currentBuild.previousBuild){
        try {
            copyArtifacts(projectName: currentBuild.projectName,
                    selector: specific("${currentBuild.previousBuild.number}"))
            def exists = fileExists("current_commit")
            if (exists) {
                def prev_commit = readFile('current_commit').trim()
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