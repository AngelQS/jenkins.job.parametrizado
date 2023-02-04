job('ejemplo-2-job-DSL') {
  description('Job DSL de ejemplo para el curso de Jenkins')
  scm {
    git('https://github.com/AngelQS/jenkins.job.parametrizado.git', 'main') { node ->
      node / gitConfigName('AngelQS')
      node / gitConfigEmail('aedwin.acuario31@gmail.com')
    }
  }
  parameters {
    stringParam('nombre', defaultValue = 'Angel', description = 'Parámetro de cadena para el Job Booleano')
    choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
    booleanParam('agente', false)
  }
  triggers {
  	cron('H/7 * * * *')
  }
  steps {
  	shell('bash jobscript.sh')
  }
  publishers {
    mailer('developer.aqs@gmail.com', true, true)
    slackNotifier {
   	  notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
