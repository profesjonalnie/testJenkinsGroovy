package nope

def buildImage(args = [:]) {

    print "abcfwefefawfwrawfawfawfafafa aaaaaaaaa"
    def credentialsId = args.credentialsId
    def credentials = [azureServicePrincipal(credentialsId: credentialsId)]

        withCredentials(credentials) {
            sh 'az login --service-principal -u $AZURE_CLIENT_ID -p $AZURE_CLIENT_SECRET -t $AZURE_TENANT_ID'
            sh "az aks list"
            sh "az aks get-credentials --name ev4aks --resource-group ev4-aks-rg" // -f - > /home/.kubeconfig"
            // sh "KUBECONFIG=/home/.kubeconfig"
            // sh "kubectl config get-contexts"
            sh "az account show --query tenantId -o tsv"
            sh "az account show --query id -o tsv"
            }
}



return this