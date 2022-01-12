#!groovy
def checkAppFileChanged(exp, branch_name){
    def GIT_FILES = sh(
            script: 'git ls-tree --full-tree -r --name-only ' + branch_name,
            returnStdout: true
    ).thim().split('\n').findAll{ it =~ exp}
    def CURRENT_COMMIT_ID = sh(
            script: 'git show-ref --hash refs/remotes/origin/' + branch_name,
            returnStdout: true
    ).thim()
    for (String file in GIT_FILES){
        println file
    }
}
return this;