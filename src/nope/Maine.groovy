package nope

import nope.ToImportFunctions
import groovy.transform.Field

@Field def abc = ""

@Field def imported = new ToImportFunctions()

@Field zmienna = ""

// Maine(String zmienna){
//     this.zmieniona = args.zmienna
// }

def installKubectl(args = [:]){
    sh 'curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"'
    sh 'install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl'
    sh 'kubectl version --client'
}

def buildImage(args = [:]) {

    print "abcfwefefawfwrawfawfawfafafa aaaaaaaaa"
    def credentialsId = args.credentialsId
    def credentials = [azureServicePrincipal(credentialsId: credentialsId)]
    def environment="ev4"

    withCredentials(credentials) {
    sh 'set +x; \
    response=$(curl -X POST -d "grant_type=client_credentials&client_id=${AZURE_CLIENT_ID}&client_secret=${AZURE_CLIENT_SECRET}&resource=https%3A%2F%2Fmanagement.azure.com%2F" https://login.microsoftonline.com/${AZURE_TENANT_ID}/oauth2/token); \
        token=$(echo $response | jq ".access_token" -r); \
        kubeconfigResponse=$(curl -X POST -H "Authorization: Bearer ${token}" -d "" https://management.azure.com/subscriptions/${AZURE_SUBSCRIPTION_ID}/resourceGroups/' + environment + '-aks-rg/providers/Microsoft.ContainerService/managedClusters/' + environment + 'aks/listClusterAdminCredential?api-version=2021-05-01); \
        echo $kubeconfigResponse | jq ".kubeconfigs[0].value" -r | base64 -d '
    }
    // importingFunc()
    return
}

def testingOne(args = [:]){
    print "GIT_URL: ${GIT_URL}, env.GIT_URL: ${env.GIT_URL}"
    print "BUILD_NUMBER: ${BUILD_NUMBER}, env.BUILD_NUMBER: ${env.BUILD_NUMBER}"    
    println "A tutaj brak danej zmiennej: ${args.zmiennaWidmo}"
    println "currentbuild.displayName: ${currentBuild.displayName}"
    print "zmienna: ${zmienna}"
    if(args.TAG_NAME){
        print "Hurrey, I've got ${args.TAG_NAME}"
    } else {
        print "Nothing I'm getting, zero TAG_NAME: ${args.TAG_NAME}"
        print args.TAG_NAME
    }
    imported.greet()
    // build(job: "fisrt/master/124")

    if(args.abc){
        println "abc was provided: ${args.abc}. So I'll override it."
        abc = args.abc
    } else {
        println "NOT provided abc, so i'm getting declared inside groovy file: ${abc}"
    }
    assert abc != null && abc != ""  :"You have to provide projectName"
}

def importingFunc(args = [:]){
    GroovyShell shell = new GroovyShell()
    def tools = shell.parse(new File('ToImportFunctions.groovy'))
    tools.greet(id: "ABCDEF")
}

def secondFunction(args = [:]){
    print "I'm in a second function"
    // sh "az aks get-credentials --name ev4aks --resource-group ev4-aks-rg --admin  --subscription Everest-Develop"
    // sh "az aks list"
    println "abc is ${abc}"
    def enableLintDockerfile = args.enableLintDockerfile?: false
    println "enableLintDockerfile gave as args? ${enableLintDockerfile}"
    println "${env.GIT_COMMIT}"
    println "${GIT_COMMIT}"
    println "${env.WORKSPACE}"
    println "${WORKSPACE}"
}

// dla większej czytelności logów
def msgFlatten(def list, def msgs) {
   list = list ?: []
   if (!(msgs instanceof String) && !(msgs instanceof GString)) {
       msgs.each { msg ->
           list = msgFlatten(list, msg)
       }
   }
   else {
       list += msgs
   }

   return  list
}