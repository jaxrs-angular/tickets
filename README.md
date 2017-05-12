**Arquitetura**

Essa aplicação usa **h2** como banco de dados e **redis** para controle de acesso.
Usa também **spring boot**, **spring security**, **spring session**, **spring data jpa**.

Usa tambem **AngularJs**,**NodeJs**,**Bower**, **bootstrap-ui** e **angular-inform**

O redis tem finalidade de compartilhar o controle de acesso a diversas instancias da aplicação.
Tanto redis  quanto h2 devem seguir instalação padrao.

O projeto usa um tomcat embarcado atendendo "em localhost:8080/"

**Gerando Ambiente de Projeto**

instale o nodejs no computador destino

No diretorio da aplicação execute a linha de comando:

/> npm install

depois

/>bower install

**Dados**

Deve haver um banco de dados chamado tickets com usuario **admin** e senha **admin** previamente criado no h2.
No primeiro acesso o sistema criará as tabelas e populará 2 usuarios para o sistema.

-  usuario "admin" senha "admin"
-  usuario "convidado" senha "convidado"

**Documentação**

A camada backend esta municiada por javadoc.

**Oque não ficou pronto por falta de tempo**

- Não foi possivel municiar a camada frontend com ngdoc.
- Não foi possível criar os testes unitarios e de teste de interace grafica.
- Não foi possivel concluir o balanceamento de carga (requisito de escalabilidade).

Para concluir o balanceamento seria necessário:

- Separar o spring security em aplicação de authenticação.
- Instalar e configurar o ngNex para autenticar e balancear a carga entre as instancias da aplicação.

Por fim,

A aplicação esta estável com todos os requisitos funcionais solicitadas.

Para completar todos os requisitos não-funcionais seriam necessários pelo menos mais 3 dias.

Atenciosamente

Daniel

