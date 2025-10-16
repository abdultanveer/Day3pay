// IAddPaypal.aidl
package com.example.day3pay;

// Declare any non-default types here with import statements

interface IAddPaypal {
   int sum2nos(int a , int b);
}

/*
steps
1. enable aidl in buildfeatures of build.gradle [module]
2. select app folder rt click - new - aidl file- name it as IAddPaypal
3. added sum2nos(int a,int b) method in the aidl file
4. in AdditionService created a  aidlBinder  and returned it in onBind
5.in manifest i added an intent filter
 */