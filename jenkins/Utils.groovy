#!groovy
def checkAppFileChanged(exp){
    def CURRENT_BRANCH = sh(
            script: "git rev-parse --abbrev-ref HEAD",
            returnStdout: true
    ).trim()

    def GIT_FILES = sh(
            script: "git ls-tree --full-tree -r --name-only ${CURRENT_BRANCH}",
            returnStdout: true
    ).thim().split('\n').findAll{ it =~ exp}

    def CURRENT_COMMIT_ID = sh(
            script: "git show-ref --hash refs/remotes/origin/ + ${CURRENT_BRANCH}",
            returnStdout: true
    ).thim()

    for (String file in GIT_FILES){
        println file
    }
}
return this;