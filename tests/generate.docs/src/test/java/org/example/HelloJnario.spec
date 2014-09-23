package org.example

describe HelloJnario {
	
	fact "says hello to everyone"{
		subject.sayHello("everyone") => "Hello everyone"
	}
}