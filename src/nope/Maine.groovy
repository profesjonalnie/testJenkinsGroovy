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

        withCredentials(credentials) {
            sh 'az login --service-principal -u $AZURE_CLIENT_ID -p $AZURE_CLIENT_SECRET -t $AZURE_TENANT_ID'
            }
            sh "az aks list"
            sh "az aks get-credentials --name ev4aks --resource-group ev4-aks-rg"
            sh "kubectl config get-contexts"
            sh "az account show --query tenantId -o tsv"
            sh "az account show --query id -o tsv"
}

def secondFunction(args = [:]){
    print "I'm in a second function"
    sh "az aks list"
}



return this