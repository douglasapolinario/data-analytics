library(rpart)
library(rpart.plot)
library(RColorBrewer)
library(rattle)

train <- read.csv("train.csv", stringsAsFactors=FALSE)
test <- read.csv("test.csv", stringsAsFactors=FALSE)

proporcao_genero_obito <- function() {
  #proporcao por geral
  proporcao_geral <- prop.table(table(train$sex))
  
  #proporcao por geral genero
  proporcao_geral_sex <- prop.table(table(train$sex, train$survived))
  
  #proporcao por obito x genero
  proporcao_obito_genero <- prop.table(table(train$sex, train$survived), 1)
  
  #proporcao por genero x obito 
  proporcao_genero_obito <- prop.table(table(train$sex, train$survived), 2)
  
  print("Proporcao Geral(%):")
  print(proporcao_geral)
  print("Proporcao Genero(%):")
  print(proporcao_geral_sex)
  print("Proporcao Obito x Genero(%):")
  print(proporcao_obito_genero)
  print("Proporcao Genero x Obito(%):")
  print(proporcao_genero_obito)
} 

quantidade_crianca <- function() {
  summary(train$age)
  train$child <- 0
  train$child[train$age < 18] <- 1
  train$survived[train$survived == "no"] <- 0
  train$survived[train$survived == "yes"] <- 1
  train$survived <- as.numeric(as.character(train$survived))
  print(aggregate(survived ~ child + sex, data=train, FUN=sum))
  print(aggregate(survived ~ child + sex, data=train, FUN=length))
  print(aggregate(survived ~ child + sex, data=train, FUN=function(x) {sum(x)/length(x)}))
}

predict_data <- function() {
  fit <- rpart(survived ~ sex + age + 
                 drink_degree + smoke_degree + 
                 sport_degree + respiratory_arrest + 
                 respiratory_insufficiency + myocardial_infarction + 
                 low_oxygen_saturation, 
               data = train, 
               method = "class")
  fancyRpartPlot(fit)
  
  prediction <- predict(fit, test, type="class")
  df_predict <- data.frame(Pacient = test$case, survived = prediction)
  write.csv(df_predict, file = "prediction.csv", row.names = FALSE)
}

proporcao_genero_obito()
quantidade_crianca()
predict_data()

train$survived[train$survived == "no"] <- 0
train$survived[train$survived == "yes"] <- 1
head(train)

train <- read.csv("train.csv", stringsAsFactors=FALSE)
test <- read.csv("test.csv", stringsAsFactors=FALSE)

head(train)
str(train)
subtrain <- subset(train, select = c("age", "sex", "respiratory_arrest", "survived"))
fit <- rpart(survived ~ age + sex 
             + respiratory_arrest ,
             data=subtrain,
             method="class")

fancyRpartPlot(fit)

prediction <- predict(fit, test, type="class")
df_predict <- data.frame(Pacient = test$case, survived = prediction)
write.csv(df_predict, file = "prediction.csv", row.names = FALSE)

summary(prediction)
prop.table(table(prediction))
