predict_data <- function() {
  library(rpart)
  library(rpart.plot)
  library(RColorBrewer)
  library(rattle)
  library(randomForest)
  
  train <- read.csv("dataset_train.csv", stringsAsFactors=FALSE)
  test <- read.csv("dataset_test.csv")
  
  #altera tipo de dados para treino no random forest
  train$sex <- as.factor(train$sex)
  train$respiratory_arrest <- as.factor(train$respiratory_arrest)
  train$respiratory_insufficiency <- as.factor(train$respiratory_insufficiency)
  train$myocardial_infarction <- as.factor(train$myocardial_infarction)
  train$low_oxygen_saturation <- as.factor(train$low_oxygen_saturation)
  train$survived[train$survived == "no"] <- 0
  train$survived[train$survived == "yes"] <- 1
  
  
  tree_fit <- rpart(survived ~ sex + age + 
                 respiratory_arrest + 
                 respiratory_insufficiency + 
                 myocardial_infarction + 
                 low_oxygen_saturation,
               data=train,
               method="class")
  fancyRpartPlot(tree_fit)
  #classificacao
  predict_classify <- predict(tree_fit, test, type="class")
  df_predict <- data.frame(case = test$case, survived = predict_classify)
  write.csv(df_predict, file = "predict_classify.csv", row.names = FALSE)
  View(read.csv("predict_classify.csv"))
  
  #probabilidade da classificacao
  predict_prob <- predict(tree_fit, test, type="prob")
  df_predict <- data.frame(case = test$case, survived = predict_prob)
  write.csv(df_predict, file = "predict_prob.csv", row.names = FALSE)
  View(read.csv("predict_prob.csv"))

  set.seed(415)
  forest_fit <- randomForest(as.numeric(survived) ~ sex + age +
                        respiratory_arrest + 
                        respiratory_insufficiency + 
                        myocardial_infarction + 
                        low_oxygen_saturation,
                      data=train, 
                      importance=TRUE, 
                      ntree=2000)
  varImpPlot(forest_fit)
  forest_predict <- predict(forest_fit, test)
  submit <- data.frame(case = test$case, survived = forest_predict)
  write.csv(submit, file = "forest.csv", row.names = FALSE)
  View(read.csv("forest.csv"))
}

predict_data()

