package nope

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
    sh 'response=$(curl -X POST -d "grant_type=client_credentials&client_id=${AZURE_CLIENT_ID}&client_secret=${AZURE_CLIENT_SECRET}&resource=https%3A%2F%2Fmanagement.azure.com%2F" https://login.microsoftonline.com/${AZURE_TENANT_ID}/oauth2/token)'

    sh 'token=$(echo $response | jq ".access_token" -r)'
    sh 'kubeconfigResponse=$(curl -X POST -H "Authorization: Bearer ${token}" -d "" https://management.azure.com/subscriptions/${AZURE_SUBSCRIPTION_ID}/resourceGroups/${environment}-aks-rg/providers/Microsoft.ContainerService/managedClusters/${environment}aks/listClusterAdminCredential?api-version=2021-05-01)'
    sh 'echo $kubeconfigResponse | jq ".kubeconfigs[0].value" -r | base64 -d > ~/.kube/config'
    sh 'cat ~/.kube/config'
    }
    // importingFunc()
}

def importingFunc(args = [:]){
    GroovyShell shell = new GroovyShell()
    def tools = shell.parse(new File('ToImportFunctions.groovy'))
    tools.greet(id: "ABCDEF")
}

def secondFunction(args = [:]){
    print "I'm in a second function"
    sh "az aks get-credentials --name ev4aks --resource-group ev4-aks-rg --admin  --subscription Everest-Develop"
    sh "az aks list"
}



return this