package nope

def buildImage(args = [:]) {

    print "abcfwefefawfwrawfawfawfafafa aaaaaaaaa"
    def credentialsId = args.credentialsId
    def credentials = [azureServicePrincipal(credentialsId: credentialsId, clientIdVariable: 'clientId', clientSecretVariable: 'clientSecret')]

        withCredentials(credentials) {
            sh "az aks get-credentials --admin --name ${clusterName}aks --resource-group ${clusterName}-aks-rg -f - >  ~/.kube/config"
            sh "az account show --query id -o tsv"
            }
}



return this