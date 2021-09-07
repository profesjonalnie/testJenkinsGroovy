package nope

def buildImage(args = [:]) {

    print "abcfwefefawfwrawfawfawfafafa aaaaaaaaa"
    def credentialsId = args.credentialsId
    def credentials = [azureServicePrincipal(credentialsId: credentialsId, clientIdVariable: 'clientId', clientSecretVariable: 'clientSecret')]

        withCredentials(credentials) {
            sh "az aks get-credentials --admin --name ev4aks --resource-group ev4-aks-rg -f - >  ~/.kubeconfig"
            sh "KUBECONFIG=~/.kubeconfig"
            sh "kubectl config get-contexts"
            }
}



return this