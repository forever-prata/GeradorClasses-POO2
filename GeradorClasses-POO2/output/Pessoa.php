<?php

class Pessoa {
	private  $nome;
	public  $idade;
	protected  $email;

	public function getNome(){
		 return $this->nome;
	}

	public function getIdade(){
		 return $this->idade;
	}

	public function getEmail(){
		 return $this->email;
	}

	public function setNome($nome){
		 $this->nome= $nome;
	}

	public function setIdade($idade){
		 $this->idade= $idade;
	}

	public function setEmail($email){
		 $this->email= $email;
	}

	public function __construct($nome, $idade, $email){
		$this->nome= $nome;
		$this->idade= $idade;
		$this->email= $email;
	}

	private function demitir(){
		return null;
	}

	public function explodir(){
		return null;
	}

	protected function matar(){
		return null;
	}

}
?>