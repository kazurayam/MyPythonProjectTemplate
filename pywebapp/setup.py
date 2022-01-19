from setuptools import setup, find_packages

setup(
    name="flaskr-kazurayam",
    version="1.1.0",
    description="Flask Tutorial",
    author="kazurayam",
    author_email="kazu@gmail.com",
    url="https://github.com/kazurayam/MyPythonTemplate",
    include_package_data=True,
    zip_safe=False,
    install_requires=open('requirements.txt').read().splitlines(),
    entry_points="""
      [console_scripts]
      hello = hello:hello 
    """,
    )
