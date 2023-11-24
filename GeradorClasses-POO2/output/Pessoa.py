class Pessoa:

	def __init__ (self, nome=None, idade=None, email=None):
		self.__nome=nome
		self.idade=idade
		self._email=email

	def get_nome(self):
		 return self.__nome

	def get_idade(self):
		 return self.idade

	def get_email(self):
		 return self._email

	def set_nome(self, nome):
		self.__nome = nome

	def set_idade(self, idade):
		self.idade = idade

	def set_email(self, email):
		self._email = email

	def __demitir(self):
		return None

	def explodir(self):
		return None

	def _matar(self):
		return None

