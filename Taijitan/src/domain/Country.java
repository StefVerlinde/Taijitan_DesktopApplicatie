package domain;

public enum Country {
    Afghanistan(0),
    Albania(1),
    Algeria(2),
    Andorra(3),
    Angola(4),
    Antarctica(5),
    Argentina(6),
    Armenia(7),
    Aruba(8),
    Australia(9),
    Austria(10),
    Azerbaijan(11),
    Bahrain(12),
    Bangladesh(13),
    Belarus(14),
    Belgium(15),
    Belize(16),
    Benin(17),
    Bhutan(18),
    Bolivia(19),
    BosniaHerzegovina(20),
    Botswana(21),
    Brazil(22),
    BruneiDarussalam(23),
    Bulgaria(24),
    BurkinaFaso(25),
    Myanmar(26),
    Burundi(27),
    Cambodia(28),
    Cameroon(29),
    Canada(30),
    CapeVerde(31),
    CentralAfricanRepublic(32),
    Chad(33),
    Chile(34),
    China(35),
    ChristmasIsland(36),
    CocosIslands(37),
    Colombia(38),
    Comoros(39),
    Congo(40),
    DemocraticRepublicCongo(41),
    CookIslands(42),
    CostaRica(43),
    Croatia(44),
    Cuba(45),
    Cyprus(46),
    CzechRepublic(47),
    Denmark(48),
    Djibouti(49),
    Timorleste(50),
    Ecuador(51),
    Egypt(52),
    ElSalvador(53),
    EquatorialGuinea(54),
    Eritrea(55),
    Estonia(56),
    Ethiopia(57),
    FalklandIslands4FverBritish(82),
    FaroeIslands(58),
    Fiji(59),
    Finland(60),
    France(61),
    FrenchPolynesia(62),
    Gabon(63),
    Gambia(64),
    Georgia(65),
    Germany(66),
    Ghana(67),
    Gibraltar(68),
    Greece(69),
    Greenland(70),
    Guatemala(71),
    Guinea(72),
    Guineabissau(73),
    Guyana(74),
    Haiti(75),
    Honduras(76),
    HongKong(77),
    Hungary(78),
    India(79),
    Indonesia(80),
    Iran(81),
    Iraq(83),
    Ireland(84),
    IsleOfMan(85),
    Israel(86),
    Italy(87),
    CoteDivoire(88),
    Japan(89),
    Jordan(90),
    Kazakhstan(91),
    Kenya(92),
    Kiribati(93),
    Kuwait(94),
    Kyrgyzstan(95),
    LaoPeoplesDR(96),
    Latvia(97),
    Lebanon(98),
    Lesotho(99),
    Liberia(100),
    Libya(101),
    Liechtenstein(102),
    Lithuania(103),
    Luxembourg(104),
    Macao(105),
    Macedonia(106),
    Madagascar(107),
    Malawi(108),
    Malaysia(109),
    Maldives(110),
    Mali(111),
    Malta(112),
    Mauritania(113),
    Mauritius(114),
    Mayotte(115),
    Mexico(116),
    Micronesia(117),
    Moldova(118),
    Monaco(119),
    Mongolia(120),
    Montenegro(121),
    Morocco(122),
    Mozambique(123),
    Namibia(124),
    Nauru(125),
    Nepal(126),
    Netherlands(127),
    NewCaledonia(128),
    NewZealand(129),
    Nicaragua(130),
    Niger(131),
    Nigeria(132),
    Niue(133),
    NorthKorea(134),
    Norway(135),
    Oman(136),
    Pakistan(137),
    Palau(138),
    Panama(139),
    PapuaNewGuinea(140),
    Paraguay(141),
    Peru(142),
    Philippines(142), //Zelde code?
    Pitcairn(143),
    Poland(144),
    Portugal(145),
    PuertoRico(146),
    Qatar(148),
    Romania(149),
    RussianFederation(150),
    Rwanda(151),
    SaintBarthélemy(152),
    Samoa(153),
    SanMarino(154),
    SaoTomeAndPrincipe(155),
    SaudiArabia(156),
    Senegal(157),
    Serbia(158),
    Seychelles(159),
    SierraLeone(160),
    Singapore(161),
    Slovakia(162),
    Slovenia(163),
    SolomonIslands(164),
    Somalia(165),
    SouthAfrica(166),
    SouthKorea(167),
    Spain(168),
    SriLanka(169),
    SaintHelena(170),
    SaintPierreMiquelon(171),
    Sudan(172),
    Suriname(173),
    Swaziland(174),
    Sweden(175),
    Switzerland(176),
    SyrianArabRepublic(177),
    Taiwan(178),
    Tajikistan(179),
    Tanzania(180),
    Thailand(181),
    Togo(182),
    Tokelau(183),
    Tonga(184),
    Tunisia(185),
    Turkey(186),
    Tuvalu(188), //187?
    UnitedArabEmirates(189),
    Uganda(190),
    UnitedKingdom(191),
    Ukraine(192),
    Uruguay(193),
    UnitedStates(194),
    Uzbekistan(195),
    Vanuatu(196),
    vaticanCity(197),
    HolySee(198),
    Venezuela(199),
    VietNam(200),
    WallisFutuna(201),
    Yemen(202),
    Zambia(203),
    Zimbabwe(204);

    private int id;

    Country(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Country getById(int id) {
        for (Country c : values()) {
            if (c.id == id) return c;
        }
        return null;
    }
}
