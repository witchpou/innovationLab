#java-oracle:jdk_8 image is used
FROM isuper/java-oracle:jdk_8 


#copy content of host directory to docker directory
ADD tomee/target/apache-tomee /usr/local/tomee 

#copy application of host directory to docker directory
ADD InnovationLab/target/innovationlab.war /usr/local/tomee/webapps/innovationlab.war


#add tomee to path variable
ENV PATH /usr/local/tomee/bin:$PATH 

#add variable CATALINA_HOME
ENV CATALINA_HOME /usr/local/tomee 


#set working directory
WORKDIR $CATALINA_HOME 

#set port which is available for host system
EXPOSE 8080 

#execute command
CMD ["catalina.sh", "run"]
