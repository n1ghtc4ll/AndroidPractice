package com.example.androidpractice

import com.example.androidpractice.anime.domain.model.Anime
import com.example.androidpractice.anime.presentation.model.AnimeUiModel

object MockData {
    private val mockDomainModel = Anime(
        malId = 0,
        url = "",
        imageUrl = "",
        title = "",
        type = "",
        episodes = 0,
        status = "",
        aired = null,
        duration = "",
        rating = null,
        score = 0.0,
        synopsis = "",
        genres = emptyList()
    )

    fun getAnimeList() = listOf(
        AnimeUiModel(
            id = 185,
            title = "Initial D First Stage",
            url = "link",
            imageUrl = "https://myanimelist.net/images/anime/1384/127972l.jpg",
            type = "TV",
            genres = listOf("Action", "Drama", "Racing").toString(),
            episodes = 26.toString(),
            score = 8.36.toString(),
            description = "Unlike his friends, Takumi Fujiwara is not particularly interested in cars, with little to no knowledge about the world of car enthusiasts and street racers. The son of a tofu shop owner, he is tasked to deliver tofu every morning without fail, driving along the mountain of Akina. Thus, conversations regarding cars or driving in general would only remind Takumi of the tiring daily routine forced upon him.\n\nOne night, the Akagi Red Suns, an infamous team of street racers, visit the town of Akina to challenge the local mountain pass. Led by their two aces, Ryousuke and Keisuke Takahashi, the Red Suns plan to conquer every racing course in Kanto, establishing themselves as the fastest crew in the region. However, much to their disbelief, one of their aces is overtaken by an old Toyota AE86 during a drive back home from Akina. After the incident, the Takahashi brothers are cautious of a mysterious driver geared with remarkable technique and experience in the local roads—the AE86 of Mount Akina.\n\n[Written by MAL Rewrite]",
            domainModel = mockDomainModel
        ),
        AnimeUiModel(
            id = 245,
            title = "Great Teacher Onizuka",
            url = "link",
            imageUrl = "https://myanimelist.net/images/anime/13/11460l.jpg",
            type = "TV",
            genres = listOf("Comedy", "Drama", "School").toString(),
            episodes = 43.toString(),
            score = 8.68.toString(),
            description = "Twenty-two-year-old Eikichi Onizuka—ex-biker gang leader, conqueror of Shonan, and virgin—has a dream: to become the greatest high school teacher in all of Japan. This isn't because of a passion for teaching, but because he wants a loving teenage wife when he's old and gray. Still, for a perverted, greedy, and lazy delinquent, there is more to Onizuka than meets the eye. So when he lands a job as the homeroom teacher of the Class 3-4 at the prestigious Holy Forest Academy—despite suplexing the Vice Principal—all of his talents are put to the test, as this class is particularly infamous.\n\nDue to their utter contempt for all teachers, the class' students use psychological warfare to mentally break any new homeroom teacher they get, forcing them to quit and leave school. However, Onizuka isn't your average teacher, and he's ready for any challenge in his way.\n\nBullying, suicide, and sexual harassment are just a few of the issues his students face daily. By tackling the roots of their problems, Onizuka supports them with his unpredictable and unconventional methods—even if it means jumping off a building to save a suicidal child. Thanks to his eccentric charm and fun-loving nature, Class 3-4 slowly learns just how enjoyable school can be when you're the pupils of the Great Teacher Onizuka.\n\n[Written by MAL Rewrite]",
            domainModel = mockDomainModel
        ),
        AnimeUiModel(
            id = 339,
            title = "Serial Experiments Lain",
            url = "link",
            imageUrl = "https://myanimelist.net/images/anime/1718/91550l.jpg",
            type = "TV",
            genres = listOf("Drama", "Mystery", "Psychological", "Sci-Fi").toString(),
            episodes = 13.toString(),
            score = 8.10.toString(),
            description = "Lain Iwakura, an awkward and introverted fourteen-year-old, is one of the many girls from her school to receive a disturbing email from her classmate Chisa Yomoda—the very same Chisa who recently committed suicide. Lain has neither the desire nor the experience to handle even basic technology; yet, when the technophobe opens the email, it leads her straight into the Wired, a virtual world of communication networks similar to what we know as the internet. Lain's life is turned upside down as she begins to encounter cryptic mysteries one after another. Strange men called the Men in Black begin to appear wherever she goes, asking her questions and somehow knowing more about her than even she herself knows. With the boundaries between reality and cyberspace rapidly blurring, Lain is plunged into more surreal and bizarre events where identity, consciousness, and perception are concepts that take on new meanings.\n\nWritten by Chiaki J. Konaka, whose other works include Texhnolyze, Serial Experiments Lain is a psychological avant-garde mystery series that follows Lain as she makes crucial choices that will affect both the real world and the Wired. In closing one world and opening another, only Lain will realize the significance of their presence.\n\n[Written by MAL Rewrite]",
            domainModel = mockDomainModel
        ),
        AnimeUiModel(
            id = 205,
            title = "Samurai Champloo",
            url = "link",
            imageUrl = "https://myanimelist.net/images/anime/1370/135212l.jpg",
            type = "TV",
            genres = listOf("Action", "Adventure", "Comedy", "Samurai").toString(),
            episodes = 26.toString(),
            score = 8.52.toString(),
            description = "Fuu Kasumi is a young and clumsy waitress who spends her days peacefully working in a small teahouse. That is, until she accidentally spills a drink all over one of her customers! With a group of samurai now incessantly harassing her, Fuu desperately calls upon another samurai in the shop, Mugen, who quickly defeats them with his wild fighting technique, utilizing movements reminiscent to that of breakdancing. Unfortunately, Mugen decides to pick a fight with the unwilling ronin Jin, who wields a more precise and traditional style of swordfighting, and the latter proves to be a formidable opponent. The only problem is, they end up destroying the entire shop as well as accidentally killing the local magistrate's son.\n\nFor their crime, the two samurai are captured and set to be executed. However, they are rescued by Fuu, who hires the duo as her bodyguards. Though she no longer has a place to return to, the former waitress wishes to find a certain samurai who smells of sunflowers and enlists the help of the now exonerated pair to do so. Despite initially disapproving of this idea, the two eventually agree to assist the girl in her quest; thus, the trio embark upon an adventure to find this mysterious warrior—that is, if Fuu can keep Mugen and Jin from killing each other.\n\nSet in an alternate Edo Period of Japan, Samurai Champloo follows the journey of these three eccentric individuals in an epic quest full of action, comedy, and dynamic sword fighting, all set to the beat of a unique hip-hop infused soundtrack.\n\n[Written by MAL Rewrite]",
            domainModel = mockDomainModel
        ),
        AnimeUiModel(
            id = 30,
            title = "Neon Genesis Evangelion",
            url = "link",
            imageUrl = "https://myanimelist.net/images/anime/1314/108941l.jpg",
            type = "TV",
            genres = listOf("Action", "Drama", "Mecha", "Psychological", "Sci-Fi").toString(),
            episodes = 26.toString(),
            score = 8.37.toString(),
            description = "Fifteen years after a cataclysmic event known as the Second Impact, the world faces a new threat: monstrous celestial beings called Angels invade Tokyo-3 one by one. Mankind is unable to defend themselves against the Angels despite utilizing their most advanced munitions and military tactics. The only hope for human salvation rests in the hands of NERV, a mysterious organization led by the cold Gendou Ikari. NERV operates giant humanoid robots dubbed \"Evangelions\" to combat the Angels with state-of-the-art advanced weaponry and protective barriers known as Absolute Terror Fields.\n\nYears after being abandoned by his father, Shinji Ikari, Gendou's 14-year-old son, returns to Tokyo-3. Shinji undergoes a perpetual internal battle against the deeply buried trauma caused by the loss of his mother and the emotional neglect he suffered at the hands of his father. Terrified to open himself up to another, Shinji's life is forever changed upon meeting 29-year-old Misato Katsuragi, a high-ranking NERV officer who shows him a free-spirited maternal kindness he has never experienced.\n\nA devastating Angel attack forces Shinji into action as Gendou reveals his true motive for inviting his son back to Tokyo-3: Shinji is the only child capable of efficiently piloting Evangelion Unit-01, a new robot that synchronizes with his biometrics. Despite the brutal psychological trauma brought about by piloting an Evangelion, Shinji defends Tokyo-3 against the angelic threat, oblivious to his father's dark machinations.\n\n[Written by MAL Rewrite]",
            domainModel = mockDomainModel
        ),
        AnimeUiModel(
            id = 43,
            title = "Ghost in the Shell",
            url = "link",
            imageUrl = "https://myanimelist.net/images/anime/10/82594l.jpg",
            type = "Movie",
            genres = listOf("Action", "Mecha", "Police", "Psychological", "Sci-Fi").toString(),
            episodes = 1.toString(),
            score = 8.27.toString(),
            description = "In the year 2029, Niihama City has become a technologically advanced metropolis. Due to great improvements in cybernetics, its citizens are able to replace their limbs with robotic parts. The world is now more interconnected than ever before, and the city's Public Security Section 9 is responsible for combating corruption, terrorism, and other dangerous threats following this shift toward globalization.\\n\\nThe strong-willed Major Motoko Kusanagi of Section 9 spearheads a case involving a mysterious hacker known only as the \"Puppet Master,\" who leaves a trail of victims stripped of their memories. Like many in this futuristic world, the Puppet Master's body is almost entirely robotic, giving them incredible power.\\n\\nAs Motoko and her subordinates follow the enigmatic criminal's trail, other parties—including Section 6—start to get involved, forcing her to confront the extremely complicated nature of the case. Pondering about various philosophical questions, such as her own life's meaning, Motoko soon realizes that the one who will provide these answers is none other than the Puppet Master themself.\\n\\n[Written by MAL Rewrite]",
            domainModel = mockDomainModel
        ),
        AnimeUiModel(
            id = 468,
            title = "Ghost in the Shell 2: Innocence",
            url = "link",
            imageUrl = "https://myanimelist.net/images/anime/10/75628l.jpg",
            type = "Movie",
            genres = listOf("Action", "Mecha", "Police", "Psychological", "Sci-Fi").toString(),
            episodes = 1.toString(),
            score = 7.78.toString(),
            description = "With Major Motoko Kusanagi missing, Section 9's Batou is assigned to investigate a string of gruesome murders—seemingly at the hands of faulty gynoids, or sex robots. But when a faulty gynoid leaves Batou a cryptic message, he begins to question the cause of their malfunctions. Suspicions of politically motivated murder and an illegal \"ghost\" quickly crop up, drawing Batou and his partner Togusa into a perilous web of conspiracy.\\n\\nAs their investigation goes on, the line between man and machine continues to blur, and reality and perception become indistinguishable. Confronting strange and dangerous foes, Batou and Togusa explore a futuristic world filled with machines and living dolls but utterly devoid of humanity.\\n\\n[Written by MAL Rewrite]",
            domainModel = mockDomainModel
        ),
        AnimeUiModel(
            id = 4898,
            title = "Black Butler",
            url = "link",
            imageUrl = "https://myanimelist.net/images/anime/1467/137783l.jpg",
            type = "TV",
            genres = listOf("Action", "Comedy", "Demons", "Fantasy", "Historical").toString(),
            episodes = 24.toString(),
            score = 7.65.toString(),
            description = "Young Ciel Phantomhive is known as \"the Queen's Guard Dog,\" taking care of the many unsettling events that occur in Victorian England for Her Majesty. Aided by Sebastian Michaelis, his loyal butler with seemingly inhuman abilities, Ciel uses whatever means necessary to get the job done. But is there more to this black-clad butler than meets the eye?\\n\\nIn Ciel's past lies a secret tragedy that enveloped him in perennial darkness—during one of his bleakest moments, he formed a contract with Sebastian, a demon, bargaining his soul in exchange for vengeance upon those who wronged him. Today, not only is Sebastian one hell of a butler, but he is also the perfect servant to carry out his master's orders—all the while anticipating the delicious meal he will eventually make of Ciel's soul. As the two work to unravel the mystery behind Ciel's chain of misfortunes, a bond forms between them that neither heaven nor hell can tear apart.\\n\\n[Written by MAL Rewrite]",
            domainModel = mockDomainModel
        )
    )
}